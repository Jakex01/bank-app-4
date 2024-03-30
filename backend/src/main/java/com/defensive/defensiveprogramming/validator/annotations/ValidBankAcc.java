package com.defensive.defensiveprogramming.validator.annotations;

import com.defensive.defensiveprogramming.validator.BankAccNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BankAccNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBankAcc {
    String message() default "Invalid bank account number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
