package com.peradnya.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        return "Summary\n" +
                "Date : " + dateTime.format(format) + "\n" +
                "Withdraw : $" + credit + "\n" +
                "Balance : $" + balance + "\n";
    }
}
