package com.webshop.ProductMS.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PRODUCT_TABLE")
public class Product {

@Id
@GeneratedValue
    private long productId;
    private String productName;
    private long productPrice;
    private long productQuantity;

}
