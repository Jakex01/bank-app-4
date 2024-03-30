package com.defensive.defensiveprogramming.controller;

import com.defensive.defensiveprogramming.mapstruct.MapStructMapper;
import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankAccountControllerImplTest {

    @Mock
    private BankClientRepository bankClientRepository;

    @Test
    void createAccount() {

    }


    @Test
    void depositMoney() {
    }

    @Test
    void withdrawMoney() {
    }

    @Test
    void transferMoney() {
    }

    @Test
    void operationHistory() {
    }
}