package com.webshop.PaymentMs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue
    private long transactionId;
    private long orderId;
    private String paymentMode;
    private String transactionNumber;
    private Instant paymentDate;
    private String paymentStatus;
    private long paymentAmount;
}
