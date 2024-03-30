package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.model.*;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import com.defensive.defensiveprogramming.model.request.DepositRequest;
import com.defensive.defensiveprogramming.model.request.TransferRequest;
import com.defensive.defensiveprogramming.model.request.WithdrawRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BankAccountService {
    public ResponseEntity<?> createAccount(@RequestBody @Valid BankClientRequest bankClient);
    public ResponseEntity<?> depositMoney(DepositRequest depositRequest);

    public ResponseEntity<?> withdrawMoney(WithdrawRequest withdrawRequest);

    ResponseEntity<?> transferMoney(TransferRequest transferRequest);

    ResponseEntity<List<Operation>> operationHistory(String bankAccount);
}
