package com.example.study_hexagonal.useCaseTests;

import com.example.study_hexagonal.application.account.port.out.LoadAccountPort;
import com.example.study_hexagonal.application.account.port.out.SaveAccountPort;
import com.example.study_hexagonal.application.account.usecase.DepositUseCaseImpl;
import com.example.study_hexagonal.domain.account.Account;
import com.example.study_hexagonal.domain.account.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepositUseCaseImplTest {
    @Mock
    private LoadAccountPort loadAccountPort;

    @Mock
    private SaveAccountPort saveAccountPort;

    @InjectMocks
    private DepositUseCaseImpl depositUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 입금_성공_테스트() {
        // Given
        String accountId = "testId";
        Account account = new Account(accountId, new Money(new BigDecimal(1000)));
        Money depositAmount = new Money(new BigDecimal(500));
        when(loadAccountPort.loadAccount(accountId)).thenReturn(account);

        // When
        depositUseCase.deposit(accountId, depositAmount);

        // Then
        verify(saveAccountPort).saveAccount(argThat(savedAccount ->
                savedAccount.getBalance().equals(new Money(new BigDecimal(1500)))
        ));
    }
}
