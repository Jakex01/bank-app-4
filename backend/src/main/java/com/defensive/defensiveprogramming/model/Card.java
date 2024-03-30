package com.defensive.defensiveprogramming.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Card {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BankClient bankClient;
    abstract double checkDebt();
    abstract void withdrawMoney();
    abstract void pay();


}
