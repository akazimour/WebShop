package com.webshop.PaymentMs.controller;


import com.webshop.PaymentMs.dto.PaymentRequest;
import com.webshop.PaymentMs.dto.PaymentResponse;
import com.webshop.PaymentMs.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

@Autowired
PaymentService paymentService;

@PostMapping
public ResponseEntity<Long>doPayment(@RequestBody PaymentRequest paymentRequest){
return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);

}
@GetMapping("/order/{id}")
public ResponseEntity<PaymentResponse> getPaymentResponse(@PathVariable long id){
    PaymentResponse paymentResponse = paymentService.getPaymentResponseById(id);
    return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
}

}
