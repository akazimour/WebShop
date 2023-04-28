package com.webshop.ProductMS.service;
import com.webshop.ProductMS.dto.ProductRequest;
import com.webshop.ProductMS.dto.ProductResponse;
import com.webshop.ProductMS.entity.Product;
import com.webshop.ProductMS.exception.ProductServiceCustomException;
import com.webshop.ProductMS.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Transactional
    @Override
    public long addProduct(ProductRequest productRequest) {

log.info("Adding product");
log.info("This is the product request {}",productRequest);

Product product =
        Product.builder()
                .productId(productRequest.getProductId())
                .productName(productRequest.getProductName())
                .productQuantity(productRequest.getProductQuantity())
                .productPrice(productRequest.getProductPrice())
                .build();
productRepository.saveAndFlush(product);

return product.getProductId();
    }
    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Get the product by id: {}", id);

        Product product = productRepository.findById(id).orElseThrow(()->new ProductServiceCustomException(" Product does not exist with id: " + id,"PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity {} for Id: {}",quantity,productId);
        Product product =productRepository.findById(productId).orElseThrow(()->new ProductServiceCustomException(" Product does not exist with id: " + productId,"PRODUCT_NOT_FOUND"));
    if (product.getProductQuantity()<quantity){
        throw new ProductServiceCustomException("Product does not have sufficient quantity", "INSUFFICIENT_QUANTITY");
    }
    product.setProductQuantity(product.getProductQuantity()-quantity);
    productRepository.save(product);
    log.info("Product quantity updated Successfully!");
    }
}
