package com.calculator;

import java.util.Scanner;

/**
 * Main application class for the Calculator
 */
public class CalculatorApp {

    private static final Calculator calculator = new Calculator();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("--gui")) {
            // Launch GUI version
            javax.swing.SwingUtilities.invokeLater(CalculatorGUI::new);
        } else {
            System.out.println("========================================");
            System.out.println("    Welcome to Simple Calculator App    ");
            System.out.println("========================================");
            System.out.println();

            if (args.length > 0 && args[0].equalsIgnoreCase("--interactive")) {
                interactiveMode();
            } else {
                menuMode();
            }
        }
    }

    private static void menuMode() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-7): ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        performAddition(scanner);
                        break;
                    case "2":
                        performSubtraction(scanner);
                        break;
                    case "3":
                        performMultiplication(scanner);
                        break;
                    case "4":
                        performDivision(scanner);
                        break;
                    case "5":
                        performSquareRoot(scanner);
                        break;
                    case "6":
                        performPower(scanner);
                        break;
                    case "7":
                        System.out.println("Thank you for using Calculator. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }

    private static void interactiveMode() {
        System.out.println("Interactive Mode: Enter operations as: <operation> <num1> <num2>");
        System.out.println("Operations: add, sub, mul, div, sqrt, pow\n");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter operation (or 'exit'): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length < 2) {
                System.out.println("Invalid input format\n");
                continue;
            }

            try {
                String operation = parts[0].toLowerCase();
                double num1 = Double.parseDouble(parts[1]);

                if (operation.equals("sqrt")) {
                    System.out.println("Result: " + calculator.squareRoot(num1) + "\n");
                } else if (parts.length >= 3) {
                    double num2 = Double.parseDouble(parts[2]);
                    performOperation(operation, num1, num2);
                } else {
                    System.out.println("Invalid input format\n");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Square Root (√)");
        System.out.println("6. Power (^)");
        System.out.println("7. Exit");
        System.out.println("========================\n");
    }

    private static void performAddition(Scanner scanner) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(scanner);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(scanner);
        System.out.println("Result: " + a + " + " + b + " = " + calculator.add(a, b) + "\n");
    }

    private static void performSubtraction(Scanner scanner) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(scanner);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(scanner);
        System.out.println("Result: " + a + " - " + b + " = " + calculator.subtract(a, b) + "\n");
    }

    private static void performMultiplication(Scanner scanner) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(scanner);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(scanner);
        System.out.println("Result: " + a + " * " + b + " = " + calculator.multiply(a, b) + "\n");
    }

    private static void performDivision(Scanner scanner) {
        System.out.print("Enter first number: ");
        double a = getDoubleInput(scanner);
        System.out.print("Enter second number: ");
        double b = getDoubleInput(scanner);
        System.out.println("Result: " + a + " / " + b + " = " + calculator.divide(a, b) + "\n");
    }

    private static void performSquareRoot(Scanner scanner) {
        System.out.print("Enter number: ");
        double a = getDoubleInput(scanner);
        System.out.println("Result: √" + a + " = " + calculator.squareRoot(a) + "\n");
    }

    private static void performPower(Scanner scanner) {
        System.out.print("Enter base number: ");
        double a = getDoubleInput(scanner);
        System.out.print("Enter exponent: ");
        double b = getDoubleInput(scanner);
        System.out.println("Result: " + a + " ^ " + b + " = " + calculator.power(a, b) + "\n");
    }

    private static void performOperation(String operation, double num1, double num2) {
        double result;
        switch (operation) {
            case "add":
                result = calculator.add(num1, num2);
                System.out.println("Result: " + result);
                break;
            case "sub":
                result = calculator.subtract(num1, num2);
                System.out.println("Result: " + result);
                break;
            case "mul":
                result = calculator.multiply(num1, num2);
                System.out.println("Result: " + result);
                break;
            case "div":
                result = calculator.divide(num1, num2);
                System.out.println("Result: " + result);
                break;
            case "pow":
                result = calculator.power(num1, num2);
                System.out.println("Result: " + result);
                break;
            default:
                System.out.println("Unknown operation: " + operation);
        }
        System.out.println();
    }

    private static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
