package com.defensive.defensiveprogramming.factory;

import com.defensive.defensiveprogramming.model.Card;
import com.defensive.defensiveprogramming.model.CreditCard;
import com.defensive.defensiveprogramming.model.DebitCard;
import com.defensive.defensiveprogramming.model.PrePaidCard;

public class UserCardFactory extends CardFactory{
    @Override
    protected Card createCreditCard(String type) {
        return switch (type) {
            case "credit" -> new CreditCard();
            case "debit" -> new DebitCard();
            case "prepaid" -> new PrePaidCard();
            default -> throw new IllegalArgumentException("Unknown credit card type");
        };
    }
}
