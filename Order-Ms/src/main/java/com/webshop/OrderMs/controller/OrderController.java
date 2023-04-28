package com.webshop.OrderMs.controller;


import com.webshop.OrderMs.dto.OrderRequest;
import com.webshop.OrderMs.dto.OrderResponse;
import com.webshop.OrderMs.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping
public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){

        long orderId = orderService.addNewOrder(orderRequest);
        log.info("Order Id: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
}

@GetMapping("/{id}")
public ResponseEntity<OrderResponse>getOrderDetailsById(@PathVariable long id){
    OrderResponse orderDetails = orderService.getOrderDetails(id);
    return new ResponseEntity<>(orderDetails,HttpStatus.OK);
}

}
