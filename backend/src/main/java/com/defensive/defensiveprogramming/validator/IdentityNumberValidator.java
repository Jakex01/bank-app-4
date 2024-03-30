package com.defensive.defensiveprogramming.validator;

import com.defensive.defensiveprogramming.validator.annotations.ValidPersonalIdentityNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentityNumberValidator implements ConstraintValidator<ValidPersonalIdentityNumber, String> {

    @Override
    public void initialize(ValidPersonalIdentityNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String validPersonalIdentityNumber, ConstraintValidatorContext constraintValidatorContext) {
        return constraintValidatorContext != null && validPersonalIdentityNumber.length() == 11 && validPersonalIdentityNumber.matches("\\d{11}");
    }
}
