package demoapp.controller;
import demoapp.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String showCalculatorForm() {
        return "calculatorForm";
    }

    @PostMapping("/calculator")
    public String calculate(@RequestParam("number1") double number1, @RequestParam("number2") double number2, @RequestParam("operation") String operation, Model model) {
        try {
            double result = calculatorService.calculate(number1, number2, operation);
            model.addAttribute("number1", number1);
            model.addAttribute("number2", number2);
            model.addAttribute("operation", operation);
            model.addAttribute("result", result);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Error: División por cero no permitida.");
        }
        return "calculatorResult";
    }
}