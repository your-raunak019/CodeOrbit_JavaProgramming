package Task2_GradeManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Student Grade Management System
 * Manages student names and marks using ArrayList,
 * calculating average, highest, and lowest marks.
 */
public class GradeManagement {

    // Student entity representation
    static class Student {
        private String name;
        private double marks;

        public Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public double getMarks() {
            return marks;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        System.out.println("=================================================");
        System.out.println("     STUDENT GRADE MANAGEMENT SYSTEM            ");
        System.out.println("=================================================");

        System.out.print("Enter total number of students to add: ");
        int totalStudents = 0;

        while (true) {
            try {
                totalStudents = scanner.nextInt();
                if (totalStudents <= 0) {
                    System.out.print("Please enter a positive integer greater than 0: ");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Enter a valid integer for student count: ");
                scanner.nextLine(); // Clear scanner buffer
            }
        }

        scanner.nextLine(); // Clear buffer newline

        // Input student details
        for (int i = 0; i < totalStudents; i++) {
            System.out.println("\n--- Entering details for Student " + (i + 1) + " ---");
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();

            double marks = -1;
            while (true) {
                try {
                    System.out.print("Enter Marks (0 - 100): ");
                    marks = scanner.nextDouble();
                    if (marks < 0 || marks > 100) {
                        System.out.println("Error: Marks should be between 0 and 100.");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid numeric input for marks.");
                    scanner.nextLine(); // Clear buffer
                }
            }
            scanner.nextLine(); // Clear buffer

            studentList.add(new Student(name, marks));
        }

        // Display Summary Report
        displaySummaryReport(studentList);

        scanner.close();
    }

    /**
     * Calculates statistics and displays formatted report
     */
    private static void displaySummaryReport(ArrayList<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("\nNo student data available.");
            return;
        }

        double totalMarks = 0;
        double highestMarks = studentList.get(0).getMarks();
        double lowestMarks = studentList.get(0).getMarks();

        String topScorer = studentList.get(0).getName();
        String lowestScorer = studentList.get(0).getName();

        for (Student student : studentList) {
            double currentMarks = student.getMarks();
            totalMarks += currentMarks;

            if (currentMarks > highestMarks) {
                highestMarks = currentMarks;
                topScorer = student.getName();
            }

            if (currentMarks < lowestMarks) {
                lowestMarks = currentMarks;
                lowestScorer = student.getName();
            }
        }

        double averageMarks = totalMarks / studentList.size();

        // Print Summary Table
        System.out.println("\n=================================================");
        System.out.println("              STUDENT GRADE REPORT               ");
        System.out.println("=================================================");
        System.out.printf("%-5s | %-25s | %-10s\n", "ID", "Student Name", "Marks");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < studentList.size(); i++) {
            System.out.printf("%-5d | %-25s | %-10.2f\n",
                    (i + 1), studentList.get(i).getName(), studentList.get(i).getMarks());
        }

        System.out.println("=================================================");
        System.out.println("                 SUMMARY STATISTICS              ");
        System.out.println("=================================================");
        System.out.printf("Total Students Analyzed : %d\n", studentList.size());
        System.out.printf("Average Class Marks     : %.2f\n", averageMarks);
        System.out.printf("Highest Score           : %.2f (%s)\n", highestMarks, topScorer);
        System.out.printf("Lowest Score            : %.2f (%s)\n", lowestMarks, lowestScorer);
        System.out.println("=================================================");
    }
}