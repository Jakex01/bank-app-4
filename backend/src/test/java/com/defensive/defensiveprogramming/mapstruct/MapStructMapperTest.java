package com.defensive.defensiveprogramming.mapstruct;

import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapStructMapperTest {

    @Test
    void bankClient() {

        BankClientRequest bankClientRequest = new BankClientRequest(
                "Jacob",
                "Kowalski",
                "PL2233",
                "s091330@student.tu.kielce.pl",
                "PL32545");

        BankClient bankClient = MapStructMapper.INSTANCE.bankClient(bankClientRequest);

        assertEquals(bankClient.getEmail(), bankClientRequest.email());
        assertEquals(bankClient.getBankAccNumber(), bankClientRequest.bankAccNumber());
        assertEquals(bankClient.getPersonalIdentityNumber(), bankClientRequest.personalIdentityNumber());
        assertEquals(bankClient.getName(), bankClientRequest.name());
        assertEquals(bankClient.getLastName(), bankClientRequest.lastName());

    }

    @Test
    void shouldThrowNullPointerExceptionWhenBankClientRequestIsNull() {
        var exp = assertThrows(NullPointerException.class, ()->{
            MapStructMapper.INSTANCE.bankClient(null);
        });
        assertEquals("BankClientRequest should not be null", exp.getMessage());
    }
}