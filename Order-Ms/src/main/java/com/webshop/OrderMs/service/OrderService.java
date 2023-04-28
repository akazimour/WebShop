package com.webshop.OrderMs.service;


import com.webshop.OrderMs.dto.OrderRequest;
import com.webshop.OrderMs.dto.OrderResponse;

public interface OrderService {
    long addNewOrder(OrderRequest orderRequest);
    OrderResponse getOrderDetails(long id);

}
