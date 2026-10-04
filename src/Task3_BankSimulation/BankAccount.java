package Task3_BankSimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates bank account data, operations (deposit/withdrawal),
 * balance validation, and transaction history tracking.
 */
public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();

        // Record initial deposit entry
        transactionHistory.add(String.format("Account opened with initial balance: $%.2f", initialDeposit));
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be greater than zero.");
            return;
        }
        this.balance += amount;
        String log = String.format("Deposited: $%.2f | New Balance: $%.2f", amount, this.balance);
        transactionHistory.add(log);
        System.out.println("Success: " + log);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return;
        }
        // Balance validation check to prevent overdrawing
        if (amount > this.balance) {
            System.out.println("Error: Insufficient funds. Available balance: $" + String.format("%.2f", this.balance));
            return;
        }
        this.balance -= amount;
        String log = String.format("Withdrew: $%.2f | Remaining Balance: $%.2f", amount, this.balance);
        transactionHistory.add(log);
        System.out.println("Success: " + log);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printTransactionHistory() {
        System.out.println("\n=================================================");
        System.out.println("         TRANSACTION HISTORY FOR " + accountNumber);
        System.out.println("=================================================");
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("-------------------------------------------------");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions recorded yet.");
        } else {
            for (int i = 0; i < transactionHistory.size(); i++) {
                System.out.println((i + 1) + ". " + transactionHistory.get(i));
            }
        }

        System.out.println("-------------------------------------------------");
        System.out.printf("Current Final Balance: $%.2f\n", balance);
        System.out.println("=================================================");
    }
}