package com.example.study_hexagonal.application.account.usecase;

import com.example.study_hexagonal.application.account.port.in.WithdrawUseCase;
import com.example.study_hexagonal.application.account.port.out.LoadAccountPort;
import com.example.study_hexagonal.application.account.port.out.SaveAccountPort;
import com.example.study_hexagonal.domain.account.Account;
import com.example.study_hexagonal.domain.account.Money;
import org.springframework.stereotype.Service;

@Service
public class WithdrawUseCaseImpl implements WithdrawUseCase {
    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    public WithdrawUseCaseImpl(LoadAccountPort loadAccountPort, SaveAccountPort saveAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.saveAccountPort = saveAccountPort;
    }

    @Override
    public void withdraw(String accountId, Money amount) {
        Account account = loadAccountPort.loadAccount(accountId);
        account.withdraw(amount);
        saveAccountPort.saveAccount(account);
    }
}