package com.defensive.defensiveprogramming.repository;

import com.defensive.defensiveprogramming.model.BankClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankClientRepository extends JpaRepository<BankClient, Long> {
    boolean existsByBankAccNumberAndEmailAndPersonalIdentityNumber(String bankAccNumber, String email, String personalIdentityNumber);
    boolean existsBankClientByBankAccNumber(String bankAccNumber);

    Optional<BankClient> findBankClientByBankAccNumber(String bankAccNumber);
}
