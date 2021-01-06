package com.peradnya.manager;

public interface Session {
    String login(String accountNumber, String pin) throws Exception;
    boolean isOnline(String sessionId);
    String getAccountNumber(String sessionId) throws Exception;
    void logout(String sessionId) throws Exception;
}
