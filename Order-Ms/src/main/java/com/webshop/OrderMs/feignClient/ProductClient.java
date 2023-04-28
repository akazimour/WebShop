package com.webshop.OrderMs.feignClient;


import com.webshop.OrderMs.exception.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-MS/product")
public interface ProductClient {

    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long productId, @RequestParam long quantity);

    @GetMapping("/price/{id}")
    public Long getPriceById(@PathVariable Long id);

    default void fallback(Exception e){
        throw new CustomException("Product Service is not available","UNAVAILABLE",500);
    }

}