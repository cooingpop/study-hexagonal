package com.example.study_hexagonal.infrastructure.adapter.in.web.api;

import com.example.study_hexagonal.application.account.port.in.DepositUseCase;
import com.example.study_hexagonal.application.account.port.in.WithdrawUseCase;
import com.example.study_hexagonal.domain.account.Money;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountApiController {
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public AccountApiController(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable String accountId, @RequestParam BigDecimal amount) {
        depositUseCase.deposit(accountId, new Money(amount));
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable String accountId, @RequestParam BigDecimal amount) {
        withdrawUseCase.withdraw(accountId, new Money(amount));
    }
}