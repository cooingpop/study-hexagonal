package com.example.study_hexagonal.application.account.port.in;

import com.example.study_hexagonal.domain.account.Money;

public interface DepositUseCase {
    void deposit(String accountId, Money amount);
}