package com.webshop.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBack(){
        return "ORDER SERVICE is take too much time to response or it is down! Please try later!";
    }
    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack(){
        return "PRODUCT SERVICE is take too much time to response or it is down! Please try later!";
    }
    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack(){
        return "PAYMENT SERVICE service is take too much time to response or it is down! Please try later!";
    }
}
