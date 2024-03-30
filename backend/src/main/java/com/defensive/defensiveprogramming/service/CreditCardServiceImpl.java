package com.defensive.defensiveprogramming.service;


import com.defensive.defensiveprogramming.factory.CardFactory;
import com.defensive.defensiveprogramming.factory.UserCardFactory;
import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.request.CardRequest;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{

    private final BankClientRepository bankClientRepository;


    @Override
    public ResponseEntity<?> createCard(CardRequest cardRequest) {




        BankClient bankClient1 = bankClientRepository
                .findBankClientByBankAccNumber(cardRequest.bankAccNumber())
                .orElseThrow(() ->
                new NullPointerException("Bank client with account number " + cardRequest.bankAccNumber() + " not found.")
        );
        CardFactory factory = new UserCardFactory();

        Card card = factory.getCreditCard(String.valueOf(cardRequest.cardType()));
        card.setBankClient(bankClient1);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}