package com.example.product_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception, WebRequest request)
    {
        Map<String,Object> errorBody=new HashMap<>();
        errorBody.put("timeStamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.NOT_FOUND);
        errorBody.put("error","Product not found");
        errorBody.put("message",exception.getMessage());
        errorBody.put("path",request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);


    }
}
