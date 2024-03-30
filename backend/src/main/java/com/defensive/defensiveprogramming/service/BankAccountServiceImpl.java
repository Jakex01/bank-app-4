package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.model.*;
import com.defensive.defensiveprogramming.mapstruct.MapStructMapper;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import com.defensive.defensiveprogramming.model.request.DepositRequest;
import com.defensive.defensiveprogramming.model.request.TransferRequest;
import com.defensive.defensiveprogramming.model.request.WithdrawRequest;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import com.defensive.defensiveprogramming.repository.OperationRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankClientRepository bankClientRepository;
    private final OperationRepository operationRepository;

    public ResponseEntity<?> createAccount(BankClientRequest bankClient) {

        BankClient bankClient1 = MapStructMapper.INSTANCE.bankClient(bankClient);

        if (bankClientRepository.existsByBankAccNumberAndEmailAndPersonalIdentityNumber(
                bankClient1.getBankAccNumber(),
                bankClient1.getEmail(),
                bankClient1.getPersonalIdentityNumber())) {

            throw new RuntimeException("Matching account found");
        }
        bankClientRepository.save(bankClient1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<?> depositMoney(DepositRequest depositRequest) {

        BankClient bankClient = bankClientRepository
                .findBankClientByBankAccNumber(depositRequest.bankAccNumber())
                .orElseThrow(() ->
                        new NullPointerException("Bank client with account number " + depositRequest.bankAccNumber() + " not found.")
                );
        try {
            Operation operation = Operation.builder()
                    .operationDate(LocalDateTime.now())
                    .amount(depositRequest.moneyToDeposit())
                    .operationType(OperationType.DEPOSIT)
                    .bankClient(bankClient)
                    .build();

            bankClient.deposit(depositRequest.moneyToDeposit(), operation);
            bankClientRepository.save(bankClient);

            operationRepository.save(operation);

            return ResponseEntity.ok("Success");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> withdrawMoney(WithdrawRequest withdrawRequest) {

        BankClient bankClient = bankClientRepository
                .findBankClientByBankAccNumber(withdrawRequest.accountNumber())
                .orElseThrow(() ->
                        new NullPointerException("Bank client with account number " + withdrawRequest.accountNumber() + " not found.")
                );

        bankClient.withdraw(withdrawRequest.moneyToWithdraw());

        Operation operation = Operation.builder()
                .operationDate(LocalDateTime.now())
                .amount(withdrawRequest.moneyToWithdraw())
                .operationType(OperationType.WITHDRAW)
                .bankClient(bankClient)
                .build();

        operationRepository.save(operation);
        bankClientRepository.save(bankClient);

        return ResponseEntity.ok("Success");
    }
    @Override
    public ResponseEntity<?> transferMoney(TransferRequest transferRequest) {


        BankClient bankClient = bankClientRepository
                .findBankClientByBankAccNumber(transferRequest.banAccountNumber())
                .orElseThrow(() ->
                        new NullPointerException("Bank client with account number " + transferRequest.banAccountNumber() + " not found.")
                );
        BankClient bankClientDest = bankClientRepository
                .findBankClientByBankAccNumber(transferRequest.bankAccountNumberDest())
                .orElseThrow(() ->
                        new NullPointerException("Bank client with account number " + transferRequest.bankAccountNumberDest() + " not found.")
                );


        bankClient.TransferMoneyTo(transferRequest.amount());
        bankClientDest.TransferMoneyFrom(transferRequest.amount());

        bankClientRepository.save(bankClientDest);
        bankClientRepository.save(bankClient);

        Operation operation = Operation.builder()
                .operationDate(LocalDateTime.now())
                .amount(transferRequest.amount())
                .operationType(OperationType.TRANSFER_INCOMING)
                .bankClient(bankClientDest)
                .build();

        Operation operation1 = Operation.builder()
                .operationDate(LocalDateTime.now())
                .amount(transferRequest.amount())
                .operationType(OperationType.TRANSFER_OUTGOING)
                .bankClient(bankClient)
                .build();

        operationRepository.save(operation1);
        operationRepository.save(operation);


        return ResponseEntity.ok("Success");

    }

    @Override
    public ResponseEntity<List<Operation>> operationHistory(String bankAccount) {

        BankClient bankClient = bankClientRepository
                .findBankClientByBankAccNumber(bankAccount)
                .orElseThrow
                        (() ->
                        new NullPointerException("Bank client with account number " + bankAccount + " not found.")
                );

        List<Operation> operations = operationRepository.findAllByBankClientBankAccNumber(bankClient);
        return ResponseEntity.ok(operations);
    }


}
