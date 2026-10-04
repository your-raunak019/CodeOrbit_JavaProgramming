package Task1_Calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Simple Calculator Program
 * Performs basic arithmetic operations with user input validation
 * and exception handling.
 */
public class SimpleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        System.out.println("=================================");
        System.out.println("   SIMPLE CALCULATOR PROGRAM    ");
        System.out.println("=================================");

        while (continueCalculating) {
            try {
                // Read first operand
                System.out.print("\nEnter first number: ");
                double num1 = scanner.nextDouble();

                // Read second operand
                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                // Display operation menu
                System.out.println("\nSelect Operation:");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.print("Enter choice (1-4): ");
                int choice = scanner.nextInt();

                double result = 0;
                boolean validChoice = true;

                // Perform selected arithmetic operation
                switch (choice) {
                    case 1:
                        result = num1 + num2;
                        System.out.printf("Result: %.2f + %.2f = %.2f\n", num1, num2, result);
                        break;
                    case 2:
                        result = num1 - num2;
                        System.out.printf("Result: %.2f - %.2f = %.2f\n", num1, num2, result);
                        break;
                    case 3:
                        result = num1 * num2;
                        System.out.printf("Result: %.2f * %.2f = %.2f\n", num1, num2, result);
                        break;
                    case 4:
                        // Exception handling for division by zero
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero is not allowed.");
                        }
                        result = num1 / num2;
                        System.out.printf("Result: %.2f / %.2f = %.2f\n", num1, num2, result);
                        break;
                    default:
                        System.out.println("Error: Invalid choice. Please select an option between 1 and 4.");
                        validChoice = false;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input format. Please enter numeric values only.");
                scanner.nextLine(); // Clear scanner buffer
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Ask user if they want to perform another calculation
            System.out.print("\nDo you want to perform another calculation? (yes/no): ");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("y")) {
                continueCalculating = false;
            }
        }

        System.out.println("\nThank you for using Simple Calculator!");
        scanner.close();
    }
}