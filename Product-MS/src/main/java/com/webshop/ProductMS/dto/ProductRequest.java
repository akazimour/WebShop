package com.webshop.ProductMS.dto;


import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @GeneratedValue
    private long productId;
    private String productName;
    private long productPrice;
    private long productQuantity;

}
