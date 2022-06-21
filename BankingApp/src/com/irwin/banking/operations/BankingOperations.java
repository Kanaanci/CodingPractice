package com.irwin.banking.operations;

import com.irwin.banking.accounts.UserAccount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingOperations {
    /* ************ Creating mock data for the app ************ */
    HashMap<String, UserAccount> accountsMap = new HashMap<>();
    Scanner scan = new Scanner(System.in);
    public void createAccountsMockData() {
        UserAccount acc1 = new UserAccount("Kanaan", "Irwin", "kanaanci@outlook.com", "Kanaanci", "pass1");
        UserAccount acc2 = new UserAccount("Gregg", "Gay", "kanaanci@outlook.com", "Gregg", "pass2");
        UserAccount acc3 = new UserAccount("Ryan", "Bitch", "kanaanci@outlook.com", "Ryan", "pass3");
        UserAccount acc4 = new UserAccount("Keegan", "Whore", "kanaanci@outlook.com", "Keegan","pass4");
        addAccountToMap(acc1, accountsMap);
        addAccountToMap(acc2, accountsMap);
        addAccountToMap(acc3, accountsMap);
        addAccountToMap(acc4, accountsMap);
    }
    /* ******************************************************** */

    private void addAccountToMap(UserAccount account, HashMap accountsMap) {
        boolean valid = (account.getUserId() != null) && account.getUserId().matches("[A-Za-z0-9_]+") && !accountsMap.containsKey(account.getUserId());

        System.out.println("Creating account for: " + account.getFirstName() + " " + account.getLastName());
        System.out.println("With username: " + account.getUserId() + "\n");

        if(valid) {
            accountsMap.put(account.getUserId(), account);
        } else{
            System.out.println("Invalid username");
            createUserInterface();
        }
    }

    public void listAccounts() {
        for (Object key: accountsMap.keySet()) {
            UserAccount x = (UserAccount) accountsMap.get(key);

            System.out.println("User: " + key);
        }
    }

    public UserAccount accountLogin(String userId, String password) {
        UserAccount acc = accountsMap.get(userId);

        if(acc != null && acc.getUserPassword().equals(password)){
            return acc;
        } else {
            System.out.println("\nIncorrect username or password");
            return null;
        }
    }

    public void withdrawMoney(UserAccount acc, BigDecimal withdrawAmount) {
        BigDecimal currentBal = acc.getBankingAccount().getBalance();

        if(currentBal.compareTo(withdrawAmount) != -1) {
            System.out.println("Withdrawing $" + withdrawAmount);
            acc.getBankingAccount().setBalance(currentBal.subtract(withdrawAmount));

            System.out.println("New balance $" + acc.getBankingAccount().getBalance());

            accountInterface(acc);
        } else {
            System.out.println("\nWithdraw amount greater than available funds. Current funds: $" + currentBal);
            accountInterface(acc);
        }
    }

    public void depositMoney(UserAccount acc, BigDecimal depositAmount) {
        BigDecimal currentBal = acc.getBankingAccount().getBalance();

        System.out.println("Depositing $" + depositAmount);
        acc.getBankingAccount().setBalance(currentBal.add(depositAmount));

        System.out.println("New balance $" + acc.getBankingAccount().getBalance());

        accountInterface(acc);
    }

    public void updatePassword(UserAccount acc, String newPassword) {
        acc.setUserPassword(newPassword);
        System.out.println("New password has been set");
    }

    public void accountInterface(UserAccount acc) {
        int userSelection = 0;

        System.out.println("\nSelect an option below:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Update password");
        System.out.println("4. Logout");

        try {
            userSelection = scan.nextInt();
        } catch (InputMismatchException ex) {
            System.out.print("Eat ass\n");
        }

        switch (userSelection) {
            case 1:
                System.out.println("How much to withdraw?: ");
                BigDecimal withdrawAmount = scan.nextBigDecimal();

                withdrawMoney(acc, withdrawAmount);
                accountInterface(acc);
            case 2:
                System.out.println("How much to deposit?: ");
                BigDecimal depositAmount = scan.nextBigDecimal();

                depositMoney(acc, depositAmount);
                accountInterface(acc);
            case 3:
                System.out.println("Enter new password: ");
                String newPass = scan.next();
                updatePassword(acc, newPass);

                accountInterface(acc);
            case 4:
                createUserInterface();
            default:
                break;
        }
    }

    public void createUserInterface() {
        String userId, userPassword;
        int userSelection = 0;

        boolean flag = true;
        while(flag) {
            System.out.println("\nSelect an option below:");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit\n");

            try {
                userSelection = scan.nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Eat ass\n");
            }

            switch (userSelection) {
                case 1:
                    System.out.print("Enter first name: ");
                    String firstName = scan.next();
                    System.out.print("Enter last name: ");
                    String lastName = scan.next();
                    System.out.print("Enter email address: ");
                    String userEmail = scan.next();
                    System.out.print("Enter username: ");
                    userId = scan.next();
                    System.out.print("Enter password: ");
                    userPassword = scan.next();

                    UserAccount acc = new UserAccount(firstName, lastName, userEmail, userId, userPassword);
                    addAccountToMap(acc, accountsMap);

                    createUserInterface();
                case 2:
                    System.out.print("Enter Username: ");
                    userId = scan.next();
                    System.out.print("Enter Password: ");
                    userPassword = scan.next();

                    UserAccount loginAcc = accountLogin(userId.toUpperCase(), userPassword);

                    if (loginAcc != null) {
                        accountInterface(loginAcc);
                    } else {
                        createUserInterface();
                    }
                case 3:
                    flag = false;
                    break;
                case 4:
                    listAccounts();
                    createUserInterface();
                default:
                    System.out.println("Invalid Input.");
                    flag = false;
            }
            scan.close();
            System.out.println("Goodbye");
        }
    }
}
