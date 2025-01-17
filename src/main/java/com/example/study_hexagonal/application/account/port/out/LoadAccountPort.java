package com.example.study_hexagonal.application.account.port.out;

import com.example.study_hexagonal.domain.account.Account;

public interface LoadAccountPort {
    Account loadAccount(String accountId);
}
