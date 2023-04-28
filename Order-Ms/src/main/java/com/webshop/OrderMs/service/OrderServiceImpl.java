package com.webshop.OrderMs.service;


import com.webshop.OrderMs.dto.*;
import com.webshop.OrderMs.entity.OrderEntity;
import com.webshop.OrderMs.exception.CustomException;
import com.webshop.OrderMs.feignClient.PaymentClient;
import com.webshop.OrderMs.feignClient.ProductClient;
import com.webshop.OrderMs.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductClient productClient;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public long addNewOrder(OrderRequest orderRequest) {
        log.info("Placing order Request checking price differences{}", orderRequest);


        if (productClient.getPriceById(orderRequest.getProductId()) == orderRequest.getPaymentAmount()) {
            productClient.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
            log.info("Creating order with status CREATED");
            OrderEntity order = OrderEntity.builder()
                    .amount(orderRequest.getPaymentAmount())
                    .orderStatus("CREATED")
                    .productId(orderRequest.getProductId())
                    .orderDate(Instant.now())
                    .quantity(orderRequest.getQuantity())
                    .build();
            orderRepository.save(order);
            log.info("Calling payment service to complete the payment");
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .orderId(order.getId())
                    .paymentMode(orderRequest.getPaymentMode())
                    .amount(orderRequest.getPaymentAmount())
                    .build();

            String orderStatus = null;
            try {
                paymentClient.doPayment(paymentRequest);
                log.info("Payment done successfully! Changing order status to PLACED.");
                orderStatus = "PLACED";
            } catch (Exception e) {
                log.error("Error occurred in payment! Changing order status to PAYMENT_FAILED.");
                orderStatus = "PAYMENT_FAILED";
            }

            order.setOrderStatus(orderStatus);
            orderRepository.save(order);

            log.info("Order placed successfully with orderId: {}", order.getId());
            return order.getId();
        }else throw new CustomException("Order request contains different price amount comparing to the product price: "+ orderRequest.getPaymentAmount(), "BAD_REQUEST", 400);
    }
    @Override
    public OrderResponse getOrderDetails(long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new CustomException("Order not found for Id: " + id, "NOT_FOUND", 404));
        log.info("Getting productDetails for order response by productId : {}",order.getProductId());

        log.info("Getting payment information from payment service");
        PaymentResponse paymentResponse =
                restTemplate.getForObject("HTTP://PAYMENT-MS/payment/order/"+order.getId(), PaymentResponse.class);

        ProductResponse productResponse =
                restTemplate.getForObject("HTTP://PRODUCT-MS/product/"+order.getProductId(), ProductResponse.class);
OrderResponse.ProductDetails productDetails =
        OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productQuantity(order.getQuantity())
                .productId(productResponse.getProductId())
                .productPrice(productResponse.getProductPrice())
                .build();

OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
        .paymentId(paymentResponse.getPaymentId())
        .paymentStatus(paymentResponse.getStatus())
        .paymentDate(paymentResponse.getPaymentDate())
        .paymentMode(paymentResponse.getPaymentMode())
        .build();

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .priceAmount(order.getAmount())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        return orderResponse;
    }
}
