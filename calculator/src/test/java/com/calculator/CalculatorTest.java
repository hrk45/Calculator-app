package com.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(5.0, calculator.add(2, 3), 0);
        assertEquals(0.0, calculator.add(-5, 5), 0);
        assertEquals(-3.0, calculator.add(-1, -2), 0);
    }

    @Test
    public void testSubtract() {
        assertEquals(1.0, calculator.subtract(5, 4), 0);
        assertEquals(-10.0, calculator.subtract(0, 10), 0);
        assertEquals(0.0, calculator.subtract(5, 5), 0);
    }

    @Test
    public void testMultiply() {
        assertEquals(15.0, calculator.multiply(3, 5), 0);
        assertEquals(0.0, calculator.multiply(0, 100), 0);
        assertEquals(-20.0, calculator.multiply(-4, 5), 0);
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, calculator.divide(10, 5), 0);
        assertEquals(0.5, calculator.divide(1, 2), 0);
        assertEquals(-2.0, calculator.divide(-10, 5), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }

    @Test
    public void testSquareRoot() {
        assertEquals(5.0, calculator.squareRoot(25), 0);
        assertEquals(0.0, calculator.squareRoot(0), 0);
        assertEquals(3.0, calculator.squareRoot(9), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSquareRootNegative() {
        calculator.squareRoot(-5);
    }

    @Test
    public void testPower() {
        assertEquals(8.0, calculator.power(2, 3), 0);
        assertEquals(1.0, calculator.power(5, 0), 0);
        assertEquals(0.25, calculator.power(2, -2), 0.0001);
    }
}
