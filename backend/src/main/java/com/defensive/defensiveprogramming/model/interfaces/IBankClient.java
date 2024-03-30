package com.defensive.defensiveprogramming.model.interfaces;

import com.defensive.defensiveprogramming.model.Operation;

import java.util.List;

public interface IBankClient {
    void deposit(double amount, Operation operation);
    void withdraw(double amount);
    List<Operation> operationHistory();
    void TransferMoneyFrom(double amount);
    void TransferMoneyTo(double amount);
}
