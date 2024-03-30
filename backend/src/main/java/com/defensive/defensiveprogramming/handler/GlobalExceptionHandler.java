package com.defensive.defensiveprogramming.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException entityNotFoundException){
        return ResponseEntity
                .badRequest()
                .body(entityNotFoundException.getMessage());
    }
}
