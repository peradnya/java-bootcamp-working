package com.peradnya.controller;

import com.peradnya.helper.Generator;
import com.peradnya.manager.*;
import com.peradnya.model.*;
import com.peradnya.view.TerminalViewCallback;

public class TerminalControllerImpl implements TerminalController {
    private final Database database;
    private final Session session;
    private final Bank bank;
    private final TerminalViewCallback view;

    private Page page;
    private String sessionId;

    public TerminalControllerImpl(TerminalViewCallback view) {
        this.database = new DatabaseImpl();
        this.session = new SessionImpl(database);
        this.bank = new BankImpl(session, database);

        this.page = Page.LOGIN;
        this.view = view;
        this.sessionId = "";
    }

    @Override
    public void login(String accountNumber, String pin) throws Exception {
        try {
            sessionId = session.login(accountNumber, pin);
            setPage(Page.TRANSACTION);
        } catch (Exception e) {
            throw new Exception("Invalid Account Number/PIN");
        }
    }

    @Override
    public void logout() {
        sessionId = "";
        setPage(Page.LOGIN);
    }

    @Override
    public void withdraw(long credit) throws Exception {
        setPage(Page.SUMMARY, bank.withdraw(sessionId, credit));
    }

    @Override
    public void preTransferDestination(String destinationAccountNumber) throws Exception {
        Account destination = database.getAccount(destinationAccountNumber);

        Transfer temp = new Transfer(session.getAccountNumber(sessionId), destination.getAccountNumber(), null, 0, 0, null);
        setPage(Page.TRANSFER_2, temp);
    }

    @Override
    public void preTransferAmount(Transfer transfer, long credit) throws Exception {
        Account source = database.getAccount(transfer.getSourceAccountNumber());

        if (credit > 1000) {
            throw new Exception("Maximum amount to withdraw is $1000");
        } else if (credit < 1) {
            throw new Exception("Minimum amount to withdraw is $1");
        } else if (source.getBalance() < credit) {
            throw new Exception("Insufficient balance $" + credit);
        } else {
            String reference = Generator.randomNumber(6);
            Transfer temp = new Transfer(transfer.getSourceAccountNumber(), transfer.getDestinationAccountNumber(), null, credit, 0, reference);
            setPage(Page.TRANSFER_3, temp);
        }
    }

    @Override
    public void transfer(Transfer transfer) throws Exception {
        setPage(Page.SUMMARY, bank.transfer(sessionId, transfer));
    }

    @Override
    public void setPage(Page page) {
        this.page = page;
        setPage(page, null);
    }

    @Override
    public void setPage(Page page, Summary summary) {
        view.onPageChanged(page, summary);
    }

    @Override
    public Page getPage() {
        return page;
    }
}
