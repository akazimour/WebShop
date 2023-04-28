package com.webshop.PaymentMs.service;

import com.webshop.PaymentMs.dto.PaymentMode;
import com.webshop.PaymentMs.dto.PaymentRequest;
import com.webshop.PaymentMs.dto.PaymentResponse;
import com.webshop.PaymentMs.entity.TransactionDetails;
import com.webshop.PaymentMs.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentRepository paymentRepository;


    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: {}",paymentRequest);


        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .transactionNumber(paymentRequest.getTransactionNumber())
                .paymentAmount(paymentRequest.getAmount())
                .build();
        paymentRepository.save(transactionDetails);
        log.info("Transaction completed with Id: {}",transactionDetails.getTransactionId());

        return transactionDetails.getTransactionId();
    }
    @Override
    public PaymentResponse getPaymentResponseById(long id) {
        TransactionDetails byOrderId = paymentRepository.findByOrderId(id);

        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(byOrderId.getOrderId())
                .paymentDate(byOrderId.getPaymentDate())
                .paymentMode(PaymentMode.valueOf(byOrderId.getPaymentMode()))
                .amount(byOrderId.getPaymentAmount())
                .status(byOrderId.getPaymentStatus())
                .build();

        return paymentResponse;
    }
}
