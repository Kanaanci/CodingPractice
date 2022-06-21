package com.irwin.banking.main;

import com.irwin.banking.operations.BankingOperations;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nWelcome to my shitty banking app!\n");

        BankingOperations bankingOperations = new BankingOperations();

        bankingOperations.createAccountsMockData();
        bankingOperations.createUserInterface();
    }
}
