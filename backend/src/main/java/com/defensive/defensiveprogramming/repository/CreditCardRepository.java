package com.defensive.defensiveprogramming.repository;

import com.defensive.defensiveprogramming.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
