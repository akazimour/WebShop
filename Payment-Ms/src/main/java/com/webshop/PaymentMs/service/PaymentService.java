package com.webshop.PaymentMs.service;


import com.webshop.PaymentMs.dto.PaymentRequest;
import com.webshop.PaymentMs.dto.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentResponseById(long id);

}
