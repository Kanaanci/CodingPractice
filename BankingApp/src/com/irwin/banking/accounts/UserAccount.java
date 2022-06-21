package com.irwin.banking.accounts;

import java.util.*;

public class UserAccount {
    private String firstName;
    private String lastName;
    private String userId;
    private String userEmail;
    private String password;
    private BankingAccount bankingAccount;

    public UserAccount(String firstName, String lastName, String userEmail, String userId, String userPassword) {
        setFirstName(firstName);
        setLastName(lastName);
        setUserEmail(userEmail);
        setUserId(userId);
        setUserPassword(userPassword);
        setBankingAccount(new BankingAccount());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId.toUpperCase();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public void setBankingAccount(BankingAccount bankingAccount) {
        this.bankingAccount = bankingAccount;
    }

    public BankingAccount getBankingAccount() {
        return this.bankingAccount;
    }
}
