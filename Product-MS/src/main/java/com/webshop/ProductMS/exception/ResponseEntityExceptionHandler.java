package com.webshop.ProductMS.exception;


import com.webshop.ProductMS.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

@ExceptionHandler(ProductServiceCustomException.class)
    ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception){
    return new ResponseEntity<>(new ErrorResponse().builder()
            .errorMessage(exception.getMessage())
            .errorCode(exception.getErrorCode())
            .build(), HttpStatus.NOT_FOUND);

}
}
