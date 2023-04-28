package com.webshop.OrderMs.repository;


import com.webshop.OrderMs.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
