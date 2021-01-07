package com.peradnya.controller;

import com.peradnya.model.Page;
import com.peradnya.model.Summary;
import com.peradnya.model.Transfer;

public interface TerminalController {
    void login(String accountNumber, String pin) throws Exception;
    void logout();

    void withdraw(long credit) throws Exception;
    void preTransferDestination(String destinationAccountNumber) throws Exception;
    void preTransferAmount(Transfer transfer, long credit) throws Exception;
    void transfer(Transfer transfer) throws Exception;

    void setPage(Page page);
    void setPage(Page page, Summary summary);
    Page getPage();
}
