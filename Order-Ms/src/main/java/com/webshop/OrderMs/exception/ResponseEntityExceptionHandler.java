package com.webshop.OrderMs.exception;


import com.webshop.OrderMs.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

@ExceptionHandler(CustomException.class)
    ResponseEntity<ErrorResponse> handleCustomException(CustomException exception){
    return new ResponseEntity<>(new ErrorResponse().builder()
            .errorMessage(exception.getMessage())
            .errorCode(exception.getErrorCode())
            .build(), HttpStatus.valueOf(exception.getStatus()));
}
}
