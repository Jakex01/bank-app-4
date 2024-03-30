package com.defensive.defensiveprogramming.service;


import com.defensive.defensiveprogramming.model.request.CardRequest;
import org.springframework.http.ResponseEntity;

public interface CreditCardService {
    ResponseEntity<?> createCard(CardRequest cardRequest);
}
