package com.defensive.defensiveprogramming.validator;

import com.defensive.defensiveprogramming.model.CreditCard;
import com.defensive.defensiveprogramming.validator.annotations.ValidCreditCard;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreditCardValidator implements ConstraintValidator<ValidCreditCard, CreditCard> {
    @Override
    public void initialize(ValidCreditCard constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreditCard creditCard, ConstraintValidatorContext constraintValidatorContext) {
        String cardNumber = creditCard.getCardNumber();

        if (cardNumber == null || cardNumber.length() != 16 || creditCard.getCreditLimit()==0 || creditCard.getCvvCode().length()!=3) {
            return false;
        }

        int sum = 0;
        boolean alternate = false;


        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);

    }
}
