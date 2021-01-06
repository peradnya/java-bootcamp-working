package com.peradnya.model;

import java.time.LocalDateTime;

public interface Summary {
    String getSourceAccountNumber();
    LocalDateTime getDateTime();
    long getCredit();
    long getBalance();
    String print();
}
