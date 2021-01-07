package com.peradnya.manager;

import com.peradnya.model.Summary;
import com.peradnya.model.Transfer;

public interface Bank {
    Summary withdraw(String sessionId, long number) throws Exception;
    Summary transfer(String sessionId, Transfer transfer) throws Exception;
}
