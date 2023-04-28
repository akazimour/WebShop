package com.webshop.OrderMs.dto;


import com.webshop.OrderMs.entity.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long orderId;
    private long amount;
    private String transactionNumber;
    private PaymentMode paymentMode;

}
