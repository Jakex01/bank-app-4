package com.defensive.defensiveprogramming.factory;

import com.defensive.defensiveprogramming.model.Card;

public abstract class CardFactory {
    protected abstract Card createCreditCard(String type);

    public Card getCreditCard(String type) {
        return createCreditCard(type);
    }
}
