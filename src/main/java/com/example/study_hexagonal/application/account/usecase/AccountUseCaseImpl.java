package com.example.study_hexagonal.application.account.usecase;

import com.example.study_hexagonal.application.account.port.in.*;
import com.example.study_hexagonal.domain.account.Money;
import org.springframework.stereotype.Service;

@Service
public class AccountUseCaseImpl implements AccountUseCase {
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public AccountUseCaseImpl(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @Override
    public void complexAccountOperation(String accountId, Money amount) {
        // 복잡한 비즈니스 로직 구현
        depositUseCase.deposit(accountId, amount);
        withdrawUseCase.withdraw(accountId, amount.subtract(amount));
    }
}