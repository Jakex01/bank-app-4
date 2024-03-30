package com.defensive.defensiveprogramming.model;
import com.defensive.defensiveprogramming.model.interfaces.IBankClient;
import com.defensive.defensiveprogramming.validator.annotations.ValidBankAcc;
import com.defensive.defensiveprogramming.validator.annotations.ValidEmail;
import com.defensive.defensiveprogramming.validator.annotations.ValidPersonalIdentityNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankClient implements IBankClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;

    @ValidEmail
    private String email;

    @ValidPersonalIdentityNumber
    private String personalIdentityNumber;

    @ValidBankAcc
    private String bankAccNumber;

    @NotNull
    private double bankBalance;

    @OneToMany(mappedBy = "bankClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> creditCards = new ArrayList<>();

    @OneToMany(mappedBy = "bankClient")
    private List<Operation> operationHistory = new ArrayList<>();
    @Override
    public void deposit(double amount, Operation operation) {
    this.bankBalance+=amount;
    this.operationHistory.add(operation);
    }

    @Override
    public void withdraw(double amount) {
    this.bankBalance-=amount;

    }

    @Override
    public List<Operation> operationHistory() {
    return  operationHistory;
    }

    @Override
    public void TransferMoneyFrom(double amount) {
        Operation operation = Operation.builder()
                .operationType(OperationType.TRANSFER_OUTGOING)
                .amount(amount)
                .build();

        this.bankBalance-=amount;
        this.operationHistory.add(operation);
    }

    @Override
    public void TransferMoneyTo(double amount) {
        Operation operation = Operation.builder()
                .operationType(OperationType.TRANSFER_INCOMING)
                .amount(amount)
                .build();

        this.bankBalance+=amount;
        this.operationHistory.add(operation);
    }



}
