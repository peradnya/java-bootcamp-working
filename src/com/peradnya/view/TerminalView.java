package com.peradnya.view;

import com.peradnya.controller.TerminalController;
import com.peradnya.controller.TerminalControllerImpl;
import com.peradnya.helper.Checker;
import com.peradnya.model.Page;
import com.peradnya.model.Summary;
import com.peradnya.model.Transfer;

import java.util.Scanner;

public class TerminalView implements TerminalViewCallback {
    private final TerminalController controller;
    private final Scanner in;

    public TerminalView() {
        controller = new TerminalControllerImpl(this);
        in = new Scanner(System.in);
    }

    public void show() {
        onPageChanged(controller.getPage(), null);
    }

    @Override
    public void onPageChanged(Page page, Summary summary) {
        switch (page) {
            case LOGIN:
                String accountNumber;
                String pin;
                do {
                    System.out.print("Enter Account Number: ");
                    accountNumber = in.nextLine();

                    try {
                        Checker.validateAccountNumber(accountNumber);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);

                do {
                    System.out.print("Enter PIN: ");
                    pin = in.nextLine();

                    try {
                        Checker.validatePin(pin);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);

                try {
                    controller.login(accountNumber, pin);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    onPageChanged(page, summary);
                }

                break;
            case TRANSACTION:
                System.out.print("1. Withdraw\n" +
                        "2. Fund Transfer\n" +
                        "3. Exit\n" +
                        "Please choose option[3]: ");
                try {
                    String option = in.nextLine();
                    if (option.isEmpty()) {
                        controller.logout();
                    }

                    int number = Integer.parseInt(option);
                    if (number == 1) {
                        controller.setPage(Page.WITHDRAW);
                    } else if (number == 2) {
                        controller.setPage(Page.TRANSFER_1);
                    } else if (number == 3) {
                        controller.logout();
                    } else {
                        onPageChanged(page, summary);
                    }
                } catch (Exception e) {
                    onPageChanged(page, summary);
                }
                break;
            case WITHDRAW:
                System.out.print("1. $10\n" +
                        "2. $50\n" +
                        "3. $100\n" +
                        "4. Other\n" +
                        "5. Back\n" +
                        "Please choose option[5]: ");
                try {
                    String option = in.nextLine();
                    if (option.isEmpty()) {
                        controller.setPage(Page.TRANSACTION);
                    }

                    int number = Integer.parseInt(option);
                    if (number == 1) {
                        controller.withdraw(10);
                    } else if (number == 2) {
                        controller.withdraw(50);
                    } else if (number == 3) {
                        controller.withdraw(100);
                    } else if (number == 4) {
                        controller.setPage(Page.OTHER_WITHDRAW);
                    } else if (number == 5) {
                        controller.setPage(Page.TRANSACTION);
                    } else {
                        onPageChanged(page, summary);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    onPageChanged(page, summary);
                }
                break;
            case OTHER_WITHDRAW:
                System.out.print("Other Withdraw\n" +
                        "Enter amount to withdraw: ");
                try {
                    String amount = in.nextLine();
                    long number = Integer.parseInt(amount);
                    controller.withdraw(number);
                } catch (NumberFormatException e) {
                    System.out.println("invalid amount");
                    onPageChanged(page, summary);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    onPageChanged(page, summary);
                }
                break;
            case TRANSFER_1:
                System.out.print("Please enter destination account and press enter to continue or \n" +
                        "press enter to go back to Transaction: ");
                String destination = in.nextLine();

                if (destination.isEmpty()) {
                    controller.setPage(Page.TRANSACTION);
                } else {
                    try {
                        Checker.validateAccountNumber(destination);
                        controller.preTransferDestination(destination);
                    } catch (Exception e) {
                        System.out.println("Invalid account");
                        controller.setPage(page, summary);
                    }
                }
                break;
            case TRANSFER_2:
                System.out.print("Please enter transfer amount and \n" +
                        "press enter to continue or \n" +
                        "press enter to go back to Transaction: ");
                String amount = in.nextLine();

                if (amount.isEmpty()) {
                    controller.setPage(Page.TRANSACTION);
                } else {
                    try {
                        int number = Integer.parseInt(amount);
                        controller.preTransferAmount((Transfer) summary, number);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount");
                        controller.setPage(page, summary);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        controller.setPage(page, summary);
                    }
                }
                break;
            case TRANSFER_3:
                String reference = ((Transfer) summary).getReferenceNumber();
                System.out.print("Reference Number: " + reference + "\n" +
                        "press enter to continue");
                in.nextLine();
                controller.setPage(Page.TRANSFER_4, summary);
                break;
            case TRANSFER_4:
                Transfer transfer = (Transfer) summary;
                System.out.print("Transfer Confirmation\n" +
                        "Destination Account : " + transfer.getDestinationAccountNumber() + "\n" +
                        "Transfer Amount     : $" + transfer.getCredit() + "\n" +
                        "Reference Number    : " + transfer.getReferenceNumber() + "\n" +
                        "\n" +
                        "1. Confirm Trx\n" +
                        "2. Cancel Trx\n" +
                        "Choose option[2]: ");
                try {
                    String option = in.nextLine();
                    if (option.isEmpty()) {
                        controller.setPage(Page.TRANSACTION);
                    }

                    int number = Integer.parseInt(option);
                    if (number == 1) {
                        controller.transfer(transfer);
                    } else if (number == 2) {
                        controller.setPage(Page.TRANSACTION);
                    } else {
                        onPageChanged(page, summary);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    onPageChanged(page, summary);
                }
                break;
            case SUMMARY:
                if (summary != null) {
                    System.out.println(summary.print());
                }

                System.out.print("1. Transaction \n" +
                        "2. Exit\n" +
                        "Choose option[2]: ");
                try {
                    String option = in.nextLine();
                    if (option.isEmpty()) {
                        controller.logout();
                    }

                    int number = Integer.parseInt(option);
                    if (number == 1) {
                        controller.setPage(Page.TRANSACTION);
                    } else if (number == 2) {
                        controller.logout();
                    } else {
                        onPageChanged(page, summary);
                    }
                } catch (Exception e) {
                    onPageChanged(page, summary);
                }
                break;
            case EXIT:
                break;
        }
    }
}
