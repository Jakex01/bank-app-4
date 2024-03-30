package com.defensive.defensiveprogramming.controller;


import com.defensive.defensiveprogramming.model.*;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import com.defensive.defensiveprogramming.model.request.DepositRequest;
import com.defensive.defensiveprogramming.model.request.TransferRequest;
import com.defensive.defensiveprogramming.model.request.WithdrawRequest;
import com.defensive.defensiveprogramming.service.BankAccountServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class BankAccountControllerImpl implements  BankAccountController{


    private final BankAccountServiceImpl bankAccountService;
    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody @Valid BankClientRequest bankClient){
        return  bankAccountService.createAccount(bankClient);
    }
    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestBody @Valid DepositRequest depositRequest){
        return  bankAccountService.depositMoney(depositRequest);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestBody @Valid WithdrawRequest withdrawRequest){
        return  bankAccountService.withdrawMoney(withdrawRequest);
    }
    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody @Valid TransferRequest transferRequest){
        return bankAccountService.transferMoney(transferRequest);
    }

    @GetMapping("/operation-history")
    public ResponseEntity<List<Operation>> operationHistory(@RequestParam @Valid String bankAccount) {
        return bankAccountService.operationHistory(bankAccount);
    }


}
