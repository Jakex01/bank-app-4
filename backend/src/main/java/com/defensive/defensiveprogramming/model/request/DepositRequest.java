package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.validator.annotations.ValidBankAcc;
import jakarta.validation.constraints.NotNull;

public record DepositRequest(
        @ValidBankAcc
        String bankAccNumber,
        @NotNull
        Double moneyToDeposit
) {
}
