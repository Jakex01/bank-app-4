package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferMoneyToRequest {
    private double amount;
    OperationType operationType;
}
