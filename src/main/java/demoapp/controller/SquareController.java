package demoapp.controller;

import demoapp.service.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SquareController {

    @Autowired
    private SquareService squareService;

    @GetMapping("/square")
    public String showSquareForm() {
        return "squareForm";
    }

    @PostMapping("/square")
    public String checkSquare(@RequestParam("number1") int number1, @RequestParam("number2") int number2, Model model) {
        boolean isSquare = squareService.isSquare(number1, number2);
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("isSquare", isSquare);
        return "squareResult";
    }
}