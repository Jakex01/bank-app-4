package com.defensive.defensiveprogramming.validator;

import com.defensive.defensiveprogramming.validator.annotations.ValidBankAcc;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigInteger;


public class BankAccNumberValidator implements ConstraintValidator<ValidBankAcc,String> {

    @Override
    public void initialize(ValidBankAcc constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String bankAccNumber, ConstraintValidatorContext constraintValidatorContext) {

        if (bankAccNumber == null || bankAccNumber.length() != 28 || !bankAccNumber.startsWith("PL")) {
            return false;
        }

        String rearrangedBankAccNumber = bankAccNumber.substring(4) + bankAccNumber.substring(0, 4); // Przesunięcie
        StringBuilder numericAccountNumber = new StringBuilder();

        for (int i = 0; i < rearrangedBankAccNumber.length(); i++) {
            numericAccountNumber.append(Character.digit(rearrangedBankAccNumber.charAt(i), 36));
        }

        BigInteger bankAccNumBigInt = new BigInteger(numericAccountNumber.toString());
        return bankAccNumBigInt.mod(BigInteger.valueOf(97)).intValue() == 1;

    }


}
