package com.defensive.defensiveprogramming.model;
import com.defensive.defensiveprogramming.model.interfaces.IBankClient;
import com.defensive.defensiveprogramming.validator.annotations.ValidBankAcc;
import com.defensive.defensiveprogramming.validator.annotations.ValidEmail;
import com.defensive.defensiveprogramming.validator.annotations.ValidPersonalIdentityNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankClient implements IBankClient, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;

    @ValidEmail
    private String email;

    @ValidPersonalIdentityNumber
    private String personalIdentityNumber;

    @ValidBankAcc
    private String bankAccNumber;

    @NotNull
    private double bankBalance;

    @Enumerated(EnumType.STRING)
    Role role;

    private String password;

    @OneToMany(mappedBy = "bankClient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> creditCards = new ArrayList<>();

    @OneToMany(mappedBy = "bankClient")
    private List<Operation> operationHistory = new ArrayList<>();
    @Override
    public void deposit(double amount, Operation operation) {
    this.bankBalance+=amount;
    this.operationHistory.add(operation);
    }

    @Override
    public void withdraw(double amount) {
    this.bankBalance-=amount;

    }

    @Override
    public List<Operation> operationHistory() {
    return  operationHistory;
    }

    @Override
    public void TransferMoneyFrom(double amount) {
        Operation operation = Operation.builder()
                .operationType(OperationType.TRANSFER_OUTGOING)
                .amount(amount)
                .build();

        this.bankBalance-=amount;
        this.operationHistory.add(operation);
    }

    @Override
    public void TransferMoneyTo(double amount) {
        Operation operation = Operation.builder()
                .operationType(OperationType.TRANSFER_INCOMING)
                .amount(amount)
                .build();

        this.bankBalance+=amount;
        this.operationHistory.add(operation);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
