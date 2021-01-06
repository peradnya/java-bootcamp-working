package com.peradnya.manager;

public interface Bank {
    void withdraw(String sessionId, long number) throws Exception;
    void transfer(String sessionId, String destinationAccountNumber, long number) throws Exception;
}
