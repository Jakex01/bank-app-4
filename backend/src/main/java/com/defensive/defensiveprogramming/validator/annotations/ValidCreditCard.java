package com.defensive.defensiveprogramming.validator.annotations;

import com.defensive.defensiveprogramming.validator.CreditCardValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CreditCardValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCreditCard {

    String message() default "Invalid Credit card";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
