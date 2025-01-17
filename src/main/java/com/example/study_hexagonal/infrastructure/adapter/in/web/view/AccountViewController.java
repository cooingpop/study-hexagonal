package com.example.study_hexagonal.infrastructure.adapter.in.web.view;

import com.example.study_hexagonal.application.account.port.in.DepositUseCase;
import com.example.study_hexagonal.application.account.port.in.WithdrawUseCase;
import com.example.study_hexagonal.domain.account.Money;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/account")
public class AccountViewController {

    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public AccountViewController(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @GetMapping
    public String showAccountPage(Model model) {
        // 여기서 계좌 정보를 가져와 모델에 추가하는 로직이 필요합니다.
        // 예를 들어:
        // Account account = accountService.getAccount(accountId);
        // model.addAttribute("account", account);
        return "account";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("accountId") String accountId,
                          @RequestParam("amount") BigDecimal amount) {
        depositUseCase.deposit(accountId, new Money(amount));
        return "redirect:/account";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("accountId") String accountId,
                           @RequestParam("amount") BigDecimal amount) {
        withdrawUseCase.withdraw(accountId, new Money(amount));
        return "redirect:/account";
    }
}
