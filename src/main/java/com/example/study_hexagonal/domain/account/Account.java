package com.example.study_hexagonal.domain.account;

public class Account {
    private final String id;
    private Money balance;

    public Account(String id, Money balance) {
        this.id = id;
        this.balance = balance;
    }

    public void deposit(Money amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        if (this.balance.isLessThan(amount)) {
            throw new IllegalStateException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    public String getId() {
        return id;
    }

    public Money getBalance() {
        return balance;
    }
}