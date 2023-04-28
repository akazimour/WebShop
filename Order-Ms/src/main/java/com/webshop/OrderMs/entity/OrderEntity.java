package com.webshop.OrderMs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDER_TABLE")
public class OrderEntity {

@Id
@GeneratedValue
    private Long id;
    private long productId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private long amount;

}
