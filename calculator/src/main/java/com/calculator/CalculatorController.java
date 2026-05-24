package com.calculator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API Controller for Calculator Operations
 */
@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
public class CalculatorController {

    private final Calculator calculator = new Calculator();

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam double a, @RequestParam double b) {
        try {
            double result = calculator.add(a, b);
            return ResponseEntity.ok(createResponse(result, a, b, "+"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createError(e.getMessage()));
        }
    }

    @PostMapping("/subtract")
    public ResponseEntity<?> subtract(@RequestParam double a, @RequestParam double b) {
        try {
            double result = calculator.subtract(a, b);
            return ResponseEntity.ok(createResponse(result, a, b, "-"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createError(e.getMessage()));
        }
    }

    @PostMapping("/multiply")
    public ResponseEntity<?> multiply(@RequestParam double a, @RequestParam double b) {
        try {
            double result = calculator.multiply(a, b);
            return ResponseEntity.ok(createResponse(result, a, b, "*"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createError(e.getMessage()));
        }
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam double a, @RequestParam double b) {
        try {
            double result = calculator.divide(a, b);
            return ResponseEntity.ok(createResponse(result, a, b, "/"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(createError(e.getMessage()));
        }
    }

    @PostMapping("/sqrt")
    public ResponseEntity<?> squareRoot(@RequestParam double a) {
        try {
            double result = calculator.squareRoot(a);
            Map<String, Object> response = new HashMap<>();
            response.put("operation", "sqrt");
            response.put("operand", a);
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(createError(e.getMessage()));
        }
    }

    @PostMapping("/power")
    public ResponseEntity<?> power(@RequestParam double base, @RequestParam double exponent) {
        try {
            double result = calculator.power(base, exponent);
            return ResponseEntity.ok(createResponse(result, base, exponent, "^"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createError(e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("message", "Calculator API is running");
        return ResponseEntity.ok(health);
    }

    private Map<String, Object> createResponse(double result, double a, double b, String operation) {
        Map<String, Object> response = new HashMap<>();
        response.put("operation", operation);
        response.put("operand1", a);
        response.put("operand2", b);
        response.put("result", result);
        return response;
    }

    private Map<String, String> createError(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }
}
