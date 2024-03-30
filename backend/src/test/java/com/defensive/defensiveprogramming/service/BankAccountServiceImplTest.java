package com.defensive.defensiveprogramming.service;

import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.Operation;
import com.defensive.defensiveprogramming.model.OperationType;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import com.defensive.defensiveprogramming.model.request.DepositRequest;
import com.defensive.defensiveprogramming.model.request.TransferRequest;
import com.defensive.defensiveprogramming.model.request.WithdrawRequest;
import com.defensive.defensiveprogramming.repository.BankClientRepository;
import com.defensive.defensiveprogramming.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Mock
    private BankClientRepository bankClientRepository;
    @Mock
    private OperationRepository operationRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {

        BankClientRequest bankClientRequest = new BankClientRequest(
                "Jacob",
                "Kowalski",
                "PL2233",
                "s091330@student.tu.kielce.pl",
                "PL32545");

        when(bankClientRepository.existsByBankAccNumberAndEmailAndPersonalIdentityNumber(
                anyString(), anyString(), anyString())).thenReturn(false);

        ResponseEntity<?> response = bankAccountService.createAccount(bankClientRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(bankClientRepository).save(any(BankClient.class));

    }
    @Test
    void createAccountDuplicateFound() {
        BankClientRequest bankClientRequest = new BankClientRequest(
                "Jacob",
                "Kowalski",
                "PL2233",
                "s091330@student.tu.kielce.pl",
                "PL32545");


        when(bankClientRepository.existsByBankAccNumberAndEmailAndPersonalIdentityNumber(
                anyString(), anyString(), anyString())).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bankAccountService.createAccount(bankClientRequest);
        });

        assertEquals("Matching account found", exception.getMessage());
        verify(bankClientRepository, never()).save(any(BankClient.class));

    }

    @Test
    void createAccountPersistsBankClientCorrectly() {
        BankClientRequest bankClientRequest = new BankClientRequest(
                "Jacob",
                "Kowalski",
                "PL2233",
                "s091330@student.tu.kielce.pl",
                "PL32545");

        ArgumentCaptor<BankClient> captor = ArgumentCaptor.forClass(BankClient.class);

        bankAccountService.createAccount(bankClientRequest);

        verify(bankClientRepository).save(captor.capture());
        BankClient savedBankClient = captor.getValue();

        assertEquals(bankClientRequest.email(), savedBankClient.getEmail());
        assertEquals(bankClientRequest.bankAccNumber(), savedBankClient.getBankAccNumber());
        assertEquals(bankClientRequest.name(), savedBankClient.getName());
        assertEquals(bankClientRequest.lastName(), savedBankClient.getLastName());
    }


    @Test
    void depositMoney() {

        DepositRequest depositRequest = new DepositRequest("PL3745223132131", 5.50);
        BankClient bankClient = new BankClient();


        when(bankClientRepository.existsBankClientByBankAccNumber(depositRequest.bankAccNumber())).thenReturn(true);
        when(bankClientRepository.findBankClientByBankAccNumber(depositRequest.bankAccNumber())).thenReturn(Optional.of(bankClient));

        ResponseEntity<?> response = bankAccountService.depositMoney(depositRequest);


        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(bankClientRepository).save(any(BankClient.class));
        verify(operationRepository).save(any(Operation.class));

    }
    @Test
    void depositMoneyAccountNotFound() {

        DepositRequest depositRequest = new DepositRequest("nonExistentBankAccNumber", 50.00);

        when(bankClientRepository.existsBankClientByBankAccNumber(depositRequest.bankAccNumber())).thenReturn(false);


        Exception exception = assertThrows(NullPointerException.class, () -> {
            bankAccountService.depositMoney(depositRequest);
        });


        assertEquals("Bank client with account number nonExistentBankAccNumber not found.", exception.getMessage());

        // Verify that no save operations were performed
        verify(bankClientRepository, never()).save(any(BankClient.class));
        verify(operationRepository, never()).save(any(Operation.class));

    }

        @Test
    void withdrawMoney() {

            WithdrawRequest withdrawRequest = new WithdrawRequest("PL3332222555", 3.0);
            BankClient bankClient = new BankClient();

            when(bankClientRepository.existsBankClientByBankAccNumber(withdrawRequest.accountNumber())).thenReturn(true);
            when(bankClientRepository.findBankClientByBankAccNumber(withdrawRequest.accountNumber())).thenReturn(Optional.of(bankClient));

            ResponseEntity<?> response = bankAccountService.withdrawMoney(withdrawRequest);


            assertEquals(HttpStatus.OK, response.getStatusCode());
            verify(bankClientRepository).save(any(BankClient.class));
            verify(operationRepository).save(any(Operation.class));

    }
    @Test
    void withdrawMoneyAccountNotFound() {

        WithdrawRequest withdrawRequest = new WithdrawRequest("PL374222223333", 50.00);


        when(bankClientRepository.existsBankClientByBankAccNumber(withdrawRequest.accountNumber())).thenReturn(false);


        Exception exception = assertThrows(NullPointerException.class, () -> {
            bankAccountService.withdrawMoney(withdrawRequest);
        });


        assertEquals("Bank client with account number PL374222223333 not found.", exception.getMessage());

        verify(bankClientRepository, never()).save(any(BankClient.class));
        verify(operationRepository, never()).save(any(Operation.class));

    }

    @Test
    void transferMoney() {
        TransferRequest transferRequest = new TransferRequest("sourceAccNumber", "destAccNumber", 100.00);
        BankClient sourceClient = new BankClient(); // Assume a proper constructor or builder is used
        BankClient destClient = new BankClient(); // Assume a proper constructor or builder is used

        when(bankClientRepository.findBankClientByBankAccNumber(transferRequest.banAccountNumber()))
                .thenReturn(Optional.of(sourceClient));
        when(bankClientRepository.findBankClientByBankAccNumber(transferRequest.bankAccountNumberDest()))
                .thenReturn(Optional.of(destClient));

        // Act
        ResponseEntity<?> response = bankAccountService.transferMoney(transferRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bankClientRepository).save(sourceClient);
        verify(bankClientRepository).save(destClient);
        verify(operationRepository, times(2)).save(any(Operation.class));
    }

    @Test
    void operationHistory() {

        List<Operation> mockOperations = Arrays.asList(
                 Operation.builder()
                        .operationType(OperationType.WITHDRAW)
                        .amount(50.00)
                        .operationDate(LocalDateTime.now().minusDays(1))
                        .build(),
                Operation.builder()
                .operationType(OperationType.TRANSFER_INCOMING)
                .amount(200.00)
                .operationDate(LocalDateTime.now().minusHours(5))
                .build()
        );
        BankClient bankClient = new BankClient();

        String testBankAccountNumber = "testAccount123";
        when(operationRepository.findAllByBankClientBankAccNumber(bankClient)).thenReturn(mockOperations);


        ResponseEntity<List<Operation>> response = bankAccountService.operationHistory(testBankAccountNumber);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(mockOperations, response.getBody());


        verify(operationRepository).findAllByBankClientBankAccNumber(bankClient);

    }

}