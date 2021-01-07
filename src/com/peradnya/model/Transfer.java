package com.peradnya.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transfer implements Summary {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private LocalDateTime dateTime;
    private long credit;
    private long balance;
    private String referenceNumber;

    public Transfer(String sourceAccountNumber, String destinationAccountNumber, LocalDateTime dateTime, long credit, long balance, String referenceNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.dateTime = dateTime;
        this.credit = credit;
        this.balance = balance;
        this.referenceNumber = referenceNumber;
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
        return "Fund Transfer Summary\n" +
                "Destination Account : " + destinationAccountNumber + "\n" +
                "Transfer Amount     : $" + credit + "\n" +
                "Reference Number    : " + referenceNumber + "\n" +
                "Balance             : $" + balance + "\n";
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }
}
