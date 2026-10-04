package Task3_BankSimulation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Entry point for the Bank Account Simulation program.
 * Provides a user-interactive console menu.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("           BANK ACCOUNT SIMULATOR                ");
        System.out.println("=================================================");

        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine().trim();

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine().trim();

        double initialDeposit = 0;
        while (true) {
            try {
                System.out.print("Enter Initial Deposit Amount: ");
                initialDeposit = scanner.nextDouble();
                if (initialDeposit < 0) {
                    System.out.println("Error: Initial deposit cannot be negative.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid numeric value.");
                scanner.nextLine(); // Clear buffer
            }
        }

        BankAccount account = new BankAccount(accNum, name, initialDeposit);
        boolean running = true;

        while (running) {
            System.out.println("\n--- BANKING OPERATIONS MENU ---");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Print Transaction History");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double dep = scanner.nextDouble();
                        account.deposit(dep);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double with = scanner.nextDouble();
                        account.withdraw(with);
                        break;
                    case 3:
                        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
                        break;
                    case 4:
                        account.printTransactionHistory();
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Error: Option must be between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input must be an integer option.");
                scanner.nextLine(); // Clear buffer
            }
        }

        account.printTransactionHistory();
        System.out.println("\nThank you for banking with us!");
        scanner.close();
    }
}