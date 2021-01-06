package com.peradnya.model;

import java.time.LocalDateTime;

public class Withdraw implements Summary {
    private String sourceAccountNumber;
    private LocalDateTime dateTime;
    private long credit;
    private long balance;

    public Withdraw(String sourceAccountNumber, LocalDateTime dateTime, long credit, long balance) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.dateTime = dateTime;
        this.credit = credit;
        this.balance = balance;
    }

    @Override
    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public long getCredit() {
        return credit;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public String print() {
        return "";
    }
}
