package com.example.study_hexagonal.application.account.usecase;

import com.example.study_hexagonal.application.account.port.in.DepositUseCase;
import com.example.study_hexagonal.application.account.port.out.LoadAccountPort;
import com.example.study_hexagonal.application.account.port.out.SaveAccountPort;
import com.example.study_hexagonal.domain.account.Account;
import com.example.study_hexagonal.domain.account.Money;
import org.springframework.stereotype.Service;

@Service
public class DepositUseCaseImpl implements DepositUseCase {
    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    public DepositUseCaseImpl(LoadAccountPort loadAccountPort, SaveAccountPort saveAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.saveAccountPort = saveAccountPort;
    }

    @Override
    public void deposit(String accountId, Money amount) {
        Account account = loadAccountPort.loadAccount(accountId);
        account.deposit(amount);
        saveAccountPort.saveAccount(account);
    }
}