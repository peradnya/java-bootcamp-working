package com.peradnya.manager;

import com.peradnya.model.Account;

public interface Database {
    void addAccount(Account account) throws Exception;
    Account getAccount(String accountNumber) throws Exception;
    void setAccountBalance(String accountNumber, long balance) throws Exception;
}
