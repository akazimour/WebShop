package com.webshop.ProductMS.service;


import com.webshop.ProductMS.dto.ProductRequest;
import com.webshop.ProductMS.dto.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    void reduceQuantity(long productId, long quantity);

}
