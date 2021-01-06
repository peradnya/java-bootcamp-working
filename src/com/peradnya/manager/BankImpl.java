package com.peradnya.manager;

import com.peradnya.model.Account;

public class BankImpl implements Bank{
    private final Session session;
    private final Database database;

    public BankImpl(Session session, Database database) {
        this.database = database;
        this.session = session;
    }

    @Override
    public void withdraw(String sessionId, long number) throws Exception {
        if (session.isOnline(sessionId)) {
            String accountNumber = session.getAccountNumber(sessionId);
            Account account = database.getAccount(accountNumber);
        }
    }

    @Override
    public void transfer(String sessionId, String destinationAccountNumber, long number) throws Exception {
        if (session.isOnline(sessionId)) {
            String accountNumber = session.getAccountNumber(sessionId);
            Account source = database.getAccount(accountNumber);
            Account destination = database.getAccount(destinationAccountNumber);
        }
    }
}
