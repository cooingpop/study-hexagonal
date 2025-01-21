package com.example.study_hexagonal.adapterIntegrationTests;

import com.example.study_hexagonal.domain.account.Account;
import com.example.study_hexagonal.domain.account.Money;
import com.example.study_hexagonal.infrastructure.adapter.out.persistence.account.AccountPersistenceAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountPersistenceAdapterIntegrationTest {

    @Autowired
    private AccountPersistenceAdapter accountPersistenceAdapter;

    @Test
    void 계좌_저장_및_로드_통합_테스트() {
        // Given
        Account account = new Account("testId", new Money(new BigDecimal(1000)));

        // When
        accountPersistenceAdapter.saveAccount(account);
        Account loadedAccount = accountPersistenceAdapter.loadAccount(account.getId());

        // Then
        assertEquals(account, loadedAccount);
    }
}
