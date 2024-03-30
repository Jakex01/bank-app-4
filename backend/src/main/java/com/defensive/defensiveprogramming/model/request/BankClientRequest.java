package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.validator.annotations.ValidBankAcc;
import com.defensive.defensiveprogramming.validator.annotations.ValidEmail;
import com.defensive.defensiveprogramming.validator.annotations.ValidPersonalIdentityNumber;
import jakarta.validation.constraints.NotBlank;

public record BankClientRequest(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @ValidPersonalIdentityNumber
        String personalIdentityNumber,
        @ValidEmail
        String email,
        @ValidBankAcc
        String bankAccNumber
){
}
