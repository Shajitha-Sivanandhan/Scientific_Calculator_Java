package com.example.calculator;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("http://localhost:5500/api/calculator")
@CrossOrigin(origins = "*") // Allow all origins for front-end access
public class CalculatorController {

    @GetMapping("/calculate")
    public double calculate(
            @RequestParam double num1,
            @RequestParam(required = false) Double num2,
            @RequestParam String operator) {
        try {
            return switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> num2 != 0 ? num1 / num2 : Double.NaN; // Handle divide by zero
                case "^" -> Math.pow(num1, num2);
                default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
            };
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Calculation error: " + e.getMessage());
        }
    }

    @GetMapping("/sqrt")
    public double sqrt(@RequestParam double num) {
        try {
            return Math.sqrt(num);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Square root error: " + e.getMessage());
        }
    }

    @GetMapping("/trig")
    public double trig(@RequestParam String func, @RequestParam double angle) {
        try {
            return switch (func.toLowerCase()) {
                case "sin" -> Math.sin(Math.toRadians(angle));
                case "cos" -> Math.cos(Math.toRadians(angle));
                case "tan" -> Math.tan(Math.toRadians(angle));
                default -> throw new IllegalArgumentException("Unsupported function: " + func);
            };
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Trigonometry error: " + e.getMessage());
        }
    }
}
