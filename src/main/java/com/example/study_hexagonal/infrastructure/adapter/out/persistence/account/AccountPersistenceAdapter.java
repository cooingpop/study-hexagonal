package com.example.study_hexagonal.infrastructure.adapter.out.persistence.account;

import com.example.study_hexagonal.application.account.port.out.LoadAccountPort;
import com.example.study_hexagonal.application.account.port.out.SaveAccountPort;
import com.example.study_hexagonal.domain.account.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {
    private final AccountMapper accountMapper;

    public AccountPersistenceAdapter(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account loadAccount(String accountId) {
        return accountMapper.selectAccount(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        Account existingAccount = accountMapper.selectAccount(account.getId());
        if (existingAccount == null) {
            accountMapper.insertAccount(account);
        } else {
            accountMapper.updateAccount(account);
        }
    }
}

