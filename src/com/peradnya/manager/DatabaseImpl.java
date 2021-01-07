package com.peradnya.manager;

import com.peradnya.model.Account;

import java.util.HashMap;

public class DatabaseImpl implements Database{
    private final HashMap<String, Account> database;

    public DatabaseImpl() {
        Account[] accounts = {
                new Account("John Due", "012108", 100, "112233"),
                new Account("Jane Doe", "932012", 30, "112244")
        };

        database = new HashMap<String, Account>();
        for (Account account : accounts) {
            database.put(account.getAccountNumber(), account);
        }
    }

    @Override
    public void addAccount(Account account) throws Exception {
        if (!database.containsKey(account.getAccountNumber())) {
            database.put(account.getAccountNumber(), account);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void setAccountBalance(String accountNumber, long balance) throws Exception {
        Account account = getAccount(accountNumber);
        account.setBalance(balance);
    }

    @Override
    public Account getAccount(String accountNumber) throws Exception {
        Account account =  database.get(accountNumber);
        if (account != null) {
            return account;
        } else {
            throw new Exception("Account not available");
        }
    }
}
