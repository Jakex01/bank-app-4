package com.defensive.defensiveprogramming.controller;

import com.defensive.defensiveprogramming.model.request.CardRequest;

import com.defensive.defensiveprogramming.service.CreditCardServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/credit-card")
public class CreditCardControllerImpl implements CreditCardController{

    private final CreditCardServiceImpl creditCardService;

    @PostMapping("/create")
    public ResponseEntity<?> createCreditCard(@RequestBody @Valid CardRequest cardRequest){
    return creditCardService.createCard(cardRequest);
    }


    @RequestMapping("/operation")
    public ResponseEntity<?> creditCardOperation() {
        return null;
    }
}
