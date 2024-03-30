package com.defensive.defensiveprogramming.validator.annotations;
import com.defensive.defensiveprogramming.validator.IdentityNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentityNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPersonalIdentityNumber {
    String message() default "Invalid identify number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
