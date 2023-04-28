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
public class OrderRequest {

    private long productId;
    private long quantity;
    private long paymentAmount;
    private PaymentMode paymentMode;

}
