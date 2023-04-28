package com.webshop.ProductMS.controller;
import com.webshop.ProductMS.dto.ProductRequest;
import com.webshop.ProductMS.dto.ProductResponse;
import com.webshop.ProductMS.service.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class ProductController {
    @Autowired
   private final ProductServiceImpl productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
 //  @PreAuthorize("hasAuthority('Admin')")


@RequestMapping(method = RequestMethod.POST)
private ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest ){
 long productId = productService.addProduct(productRequest);
    return new ResponseEntity<>(productId, HttpStatus.CREATED);
}

@GetMapping("/{id}")
public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
    ProductResponse productResponse = productService.getProductById(id);
    return new ResponseEntity<>(productResponse,HttpStatus.OK);
}

@GetMapping("/price/{id}")
public Long getPriceById(@PathVariable Long id){
        ProductResponse productResponse = productService.getProductById(id);
        return productResponse.getProductPrice();
    }

@PutMapping("/reduceQuantity/{productId}")
public ResponseEntity<Void> reduceQuantity(@PathVariable long productId, @RequestParam long quantity){
    productService.reduceQuantity(productId,quantity);
    return new ResponseEntity<>(HttpStatus.OK);

}


}
