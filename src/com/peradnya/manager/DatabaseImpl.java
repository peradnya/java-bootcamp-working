package com.peradnya.manager;

import com.peradnya.model.Account;

import java.util.HashMap;

public class DatabaseImpl implements Database{
    private final HashMap<String, Account> database;

    public DatabaseImpl() {
        database = new HashMap<String, Account>();
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
            throw new Exception();
        }
    }
}
