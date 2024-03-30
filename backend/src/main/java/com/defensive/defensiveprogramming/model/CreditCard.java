package com.defensive.defensiveprogramming.model;

import com.defensive.defensiveprogramming.validator.annotations.ValidCreditCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@ValidCreditCard
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cvvCode;
    private double creditLimit;
    private double moneyTaken;
    private LocalDateTime creditCardValidationDate;



//    private List<Operation> creditCardOperationsMonthly;

    @Override
    public double checkDebt() {
        return this.moneyTaken;
    }

    @Override
    public void withdrawMoney() {


    }

    @Override
    public void pay() {

    }
}
