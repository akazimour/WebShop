package com.webshop.ProductMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private long productId;
    private String productName;
    private long productPrice;
    private long productQuantity;

}
