package com.calculator;

/**
 * Simple Calculator class for basic arithmetic operations
 */
public class Calculator {

    /**
     * Adds two numbers
     * @param a First number
     * @param b Second number
     * @return Sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts two numbers
     * @param a First number
     * @param b Second number
     * @return Difference of a and b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers
     * @param a First number
     * @param b Second number
     * @return Product of a and b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides two numbers
     * @param a First number
     * @param b Second number (divisor)
     * @return Quotient of a divided by b
     * @throws IllegalArgumentException if b is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }

    /**
     * Calculates the square root of a number
     * @param a Number to calculate square root of
     * @return Square root of a
     * @throws IllegalArgumentException if a is negative
     */
    public double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(a);
    }

    /**
     * Calculates the power of a number
     * @param base Base number
     * @param exponent Exponent
     * @return base raised to the power of exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
