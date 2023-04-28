package com.webshop.OrderMs.dto;


import com.webshop.OrderMs.entity.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long priceAmount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductDetails {

        private Long productId;
        private String productName;
        private long productPrice;
        private long productQuantity;

    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentDetails {

        private Long paymentId;
        private PaymentMode paymentMode;
        private String paymentStatus;
        private Instant paymentDate;

    }
}
