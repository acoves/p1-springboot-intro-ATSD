package demoapp.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double calculate(double number1, double number2, String operation) {
        switch (operation) {
            case "add":
                return number1 + number2;
            case "subtract":
                return number1 - number2;
            case "multiply":
                return number1 * number2;
            case "divide":
                if (number2 != 0) {
                    return number1 / number2;
                } else {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
    }
}