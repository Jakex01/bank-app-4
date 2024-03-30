package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.validator.annotations.ValidBankAcc;
import jakarta.validation.constraints.NotNull;

public record TransferRequest(
        @ValidBankAcc
        String banAccountNumber,
        @ValidBankAcc
        String bankAccountNumberDest,
        @NotNull
        double amount
) {
}
