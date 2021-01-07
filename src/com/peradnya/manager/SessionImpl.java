package com.peradnya.manager;

import com.peradnya.model.Account;

import java.util.HashMap;
import java.util.UUID;

public class SessionImpl implements Session {
    private final HashMap<String, String> sessions;

    private final Database database;
    public SessionImpl(Database database) {
        this.database = database;
        this.sessions = new HashMap<String, String>();
    }

    @Override
    public String login(String accountNumber, String pin) throws Exception {
        Account account = database.getAccount(accountNumber);
        if (account.getPin().compareTo(pin) == 0) {
            String uuid = UUID.randomUUID().toString();
            sessions.put(uuid, accountNumber);
            return uuid;
        } else {
            throw new Exception("Invalid pin");
        }
    }

    @Override
    public boolean isOnline(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    @Override
    public String getAccountNumber(String sessionId) throws Exception {
        if (isOnline(sessionId)) {
            return sessions.get(sessionId);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void logout(String sessionId) throws Exception {
        if (sessions.remove(sessionId) == null) {
            throw new Exception();
        }
    }
}
