package com.webshop.OrderMs.feignClient;



import com.webshop.OrderMs.dto.PaymentRequest;
import com.webshop.OrderMs.exception.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-MS/payment")
public interface PaymentClient {

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception e){
        throw new CustomException("Payment Service is not available","UNAVAILABLE",500);
    }
}
