package com.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI version of the Calculator using Java Swing
 */
public class CalculatorGUI extends JFrame {

    private Calculator calculator;
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JButton sqrtButton;
    private JButton powerButton;

    private double firstNumber = 0;
    private String currentOperation = "";
    private boolean isNewNumber = true;

    public CalculatorGUI() {
        this.calculator = new Calculator();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Calculator App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(new Color(30, 30, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(30, 30, 30));

        // Display Panel
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        displayPanel.setBackground(new Color(50, 50, 50));
        displayPanel.setPreferredSize(new Dimension(380, 80));

        displayField = new JTextField("0");
        displayField.setFont(new Font("Arial", Font.BOLD, 32));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setBackground(new Color(70, 70, 70));
        displayField.setForeground(Color.WHITE);
        displayField.setEditable(false);
        displayField.setPreferredSize(new Dimension(360, 60));

        displayPanel.add(displayField);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i), new Color(60, 60, 60), Color.WHITE);
            final int number = i;
            numberButtons[i].addActionListener(e -> onNumberClick(number));
        }

        // Create operation buttons
        operationButtons = new JButton[4];
        String[] ops = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operationButtons[i] = createButton(ops[i], new Color(255, 140, 0), Color.WHITE);
            final String op = ops[i];
            operationButtons[i].addActionListener(e -> onOperationClick(op));
        }

        // Special buttons
        equalsButton = createButton("=", new Color(0, 150, 136), Color.WHITE);
        equalsButton.addActionListener(e -> onEqualsClick());

        clearButton = createButton("C", new Color(244, 67, 54), Color.WHITE);
        clearButton.addActionListener(e -> onClearClick());

        deleteButton = createButton("DEL", new Color(244, 67, 54), Color.WHITE);
        deleteButton.addActionListener(e -> onDeleteClick());

        sqrtButton = createButton("√", new Color(76, 175, 80), Color.WHITE);
        sqrtButton.addActionListener(e -> onSqrtClick());

        powerButton = createButton("^", new Color(156, 39, 176), Color.WHITE);
        powerButton.addActionListener(e -> onPowerClick());

        JButton decimalButton = createButton(".", new Color(60, 60, 60), Color.WHITE);
        decimalButton.addActionListener(e -> onDecimalClick());

        // Add buttons to panel in calculator layout
        // Row 1: C, DEL, sqrt, /
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(operationButtons[3]); // /

        // Row 2: 7, 8, 9, *
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operationButtons[2]); // *

        // Row 3: 4, 5, 6, -
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(operationButtons[1]); // -

        // Row 4: 1, 2, 3, +
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(operationButtons[0]); // +

        // Row 5: 0, ., ^, =
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(decimalButton);
        buttonPanel.add(powerButton);
        buttonPanel.add(equalsButton);

        mainPanel.add(displayPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        return button;
    }

    private void onNumberClick(int number) {
        String currentText = displayField.getText();

        if (isNewNumber) {
            displayField.setText(String.valueOf(number));
            isNewNumber = false;
        } else {
            if (currentText.equals("0")) {
                displayField.setText(String.valueOf(number));
            } else {
                displayField.setText(currentText + number);
            }
        }
    }

    private void onDecimalClick() {
        String currentText = displayField.getText();
        if (!currentText.contains(".")) {
            displayField.setText(currentText + ".");
            isNewNumber = false;
        }
    }

    private void onOperationClick(String operation) {
        try {
            double currentNumber = Double.parseDouble(displayField.getText());

            if (!currentOperation.isEmpty()) {
                calculateResult();
            } else {
                firstNumber = currentNumber;
            }

            currentOperation = operation;
            isNewNumber = true;
        } catch (NumberFormatException e) {
            displayField.setText("Error");
        }
    }

    private void onEqualsClick() {
        try {
            calculateResult();
            currentOperation = "";
            isNewNumber = true;
        } catch (Exception e) {
            displayField.setText("Error");
        }
    }

    private void calculateResult() {
        try {
            double secondNumber = Double.parseDouble(displayField.getText());
            double result = 0;

            switch (currentOperation) {
                case "+":
                    result = calculator.add(firstNumber, secondNumber);
                    break;
                case "-":
                    result = calculator.subtract(firstNumber, secondNumber);
                    break;
                case "*":
                    result = calculator.multiply(firstNumber, secondNumber);
                    break;
                case "/":
                    result = calculator.divide(firstNumber, secondNumber);
                    break;
                case "^":
                    result = calculator.power(firstNumber, secondNumber);
                    break;
                default:
                    return;
            }

            displayField.setText(formatResult(result));
            firstNumber = result;
        } catch (IllegalArgumentException e) {
            displayField.setText("Error: " + e.getMessage());
        }
    }

    private void onSqrtClick() {
        try {
            double number = Double.parseDouble(displayField.getText());
            double result = calculator.squareRoot(number);
            displayField.setText(formatResult(result));
            isNewNumber = true;
        } catch (IllegalArgumentException e) {
            displayField.setText("Error: " + e.getMessage());
        }
    }

    private void onPowerClick() {
        try {
            double currentNumber = Double.parseDouble(displayField.getText());
            if (!currentOperation.isEmpty()) {
                calculateResult();
            } else {
                firstNumber = currentNumber;
            }
            currentOperation = "^";
            isNewNumber = true;
        } catch (NumberFormatException e) {
            displayField.setText("Error");
        }
    }

    private void onClearClick() {
        displayField.setText("0");
        firstNumber = 0;
        currentOperation = "";
        isNewNumber = true;
    }

    private void onDeleteClick() {
        String currentText = displayField.getText();
        if (currentText.length() > 1) {
            displayField.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            displayField.setText("0");
            isNewNumber = true;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.10g", result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
