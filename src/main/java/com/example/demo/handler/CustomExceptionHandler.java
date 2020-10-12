package com.example.demo.handler;

import com.example.demo.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleBalanceException(IllegalArgumentException ex) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, error.getStatus());
    }
}
