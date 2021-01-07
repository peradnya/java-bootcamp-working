package com.peradnya.helper;

public class Checker {
    public static void validateAccountNumber(String accountNumber) throws Exception {
        if (accountNumber.length() != 6) {
            throw new Exception("Account Number should have 6 digits length");
        } else {
            try {
                Integer.parseInt(accountNumber);
            } catch (Exception e) {
                throw new Exception("Account Number should only contains numbers");
            }
        }
    }

    public static void validatePin(String pin) throws Exception {
        if (pin.length() != 6) {
            throw new Exception("PIN should have 6 digits length");
        } else {
            try {
                Integer.parseInt(pin);
            } catch (Exception e) {
                throw new Exception("PIN should only contains numbers");
            }
        }
    }
}
