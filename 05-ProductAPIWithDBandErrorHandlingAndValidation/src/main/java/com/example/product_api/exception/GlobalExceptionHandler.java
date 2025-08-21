package com.example.product_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.*;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception, WebRequest request)
    {
        Map<String,Object> errorBody=new HashMap<>();
        errorBody.put("timeStamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        errorBody.put("error","Internal Server Error");
        errorBody.put("message",exception.getMessage());
        errorBody.put("path",request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception, WebRequest request)
    {
        Map<String,Object> errorBody=new HashMap<>();
        errorBody.put("timeStamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST);
        errorBody.put("error","Input Validation Failed");
        Map<String,Object> validationError=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error->
                {
                String fieldName=((FieldError) error).getField();
                String fieldError=error.getDefaultMessage();
                validationError.put(fieldName,fieldError);

                });
        errorBody.put("message",validationError);
        errorBody.put("path",request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);


    }
}
