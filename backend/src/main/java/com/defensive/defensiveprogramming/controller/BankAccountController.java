package com.defensive.defensiveprogramming.controller;

import com.defensive.defensiveprogramming.model.*;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import com.defensive.defensiveprogramming.model.request.DepositRequest;
import com.defensive.defensiveprogramming.model.request.TransferRequest;
import com.defensive.defensiveprogramming.model.request.WithdrawRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BankAccountController {

    public ResponseEntity<?> createAccount(@RequestBody @Valid BankClientRequest bankClient);
    public ResponseEntity<?> depositMoney(@RequestBody @Valid DepositRequest depositRequest);
     public ResponseEntity<?> withdrawMoney(@RequestBody @Valid WithdrawRequest withdrawRequest);

    public ResponseEntity<?> transferMoney(@RequestBody @Valid TransferRequest transferRequest);
    public ResponseEntity<List<Operation>> operationHistory(@RequestParam @NotBlank String bankAccount);

}
