package com.defensive.defensiveprogramming.model;

public class PrePaidCard extends Card{
    @Override
    public double checkDebt() {
        return 0;
    }

    @Override
    public void withdrawMoney() {

    }

    @Override
    public void pay() {

    }
}
