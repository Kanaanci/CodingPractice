package com.irwin.banking.accounts;

import java.math.BigDecimal;
import java.util.UUID;

public class BankingAccount {
    private String accountNumber;
    private BigDecimal balance = new BigDecimal("0.0");

    public BankingAccount() {
        generateAccountNumber();
        setBalance(BigDecimal.valueOf(0.0));
    }

    //Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void generateAccountNumber() {
        this.accountNumber = UUID.randomUUID().toString();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
