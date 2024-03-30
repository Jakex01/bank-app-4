package com.defensive.defensiveprogramming.model.request;

import com.defensive.defensiveprogramming.model.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CardRequest(
        String bankAccNumber,
        @Enumerated(EnumType.STRING)
        CardType cardType
) {
}
