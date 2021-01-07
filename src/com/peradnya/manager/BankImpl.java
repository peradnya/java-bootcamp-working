package com.peradnya.manager;

import com.peradnya.model.Account;
import com.peradnya.model.Summary;
import com.peradnya.model.Transfer;
import com.peradnya.model.Withdraw;

import java.time.LocalDateTime;

public class BankImpl implements Bank{
    private final Session session;
    private final Database database;

    public BankImpl(Session session, Database database) {
        this.database = database;
        this.session = session;
    }

    @Override
    public Summary withdraw(String sessionId, long number) throws Exception {
        if (session.isOnline(sessionId)) {
            String accountNumber = session.getAccountNumber(sessionId);
            Account account = database.getAccount(accountNumber);

            if (number > 1000) {
                throw new Exception("Maximum amount to withdraw is $1000");
            } else if (number % 10 != 0) {
                throw new Exception("Invalid amount");
            } else if (account.getBalance() < number) {
                throw new Exception("Insufficient balance $" + number);
            } else {
                database.setAccountBalance(accountNumber, account.getBalance() - number);

                Account afterTransaction = database.getAccount(accountNumber);
                return new Withdraw(afterTransaction.getAccountNumber(), LocalDateTime.now(), number, afterTransaction.getBalance());
            }
        } else {
            throw new Exception("Session invalid");
        }
    }

    @Override
    public Summary transfer(String sessionId, Transfer transfer) throws Exception {
        if (session.isOnline(sessionId)) {
            String accountNumber = session.getAccountNumber(sessionId);
            Account source = database.getAccount(accountNumber);
            Account destination = database.getAccount(transfer.getDestinationAccountNumber());

            if (transfer.getCredit() > 1000) {
                throw new Exception("Maximum amount to withdraw is $1000");
            } else if (transfer.getCredit() < 1) {
                throw new Exception("Minimum amount to withdraw is $1");
            } else if (source.getBalance() < transfer.getCredit()) {
                throw new Exception("Insufficient balance $" + transfer.getCredit());
            } else {
                database.setAccountBalance(source.getAccountNumber(), source.getBalance() - transfer.getCredit());
                database.setAccountBalance(destination.getAccountNumber(), destination.getBalance() + transfer.getCredit());

                return new Transfer(transfer.getSourceAccountNumber(),
                        transfer.getDestinationAccountNumber(),
                        LocalDateTime.now(),
                        transfer.getCredit(),
                        source.getBalance(),
                        transfer.getReferenceNumber());
            }
        } else {
            throw new Exception("Session invalid");
        }
    }
}
