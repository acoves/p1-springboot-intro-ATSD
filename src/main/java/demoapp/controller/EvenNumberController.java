package demoapp.controller;

import demoapp.service.EvenNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EvenNumberController {

    @Autowired
    private EvenNumberService evenNumberService;

    @GetMapping("/evenNumber")
    public String showEvenNumberForm() {
        return "evenNumberForm";
    }

    @PostMapping("/evenNumber")
    public String checkEvenNumber(@RequestParam("number") int number, Model model) {
        boolean isEven = evenNumberService.isEven(number);
        model.addAttribute("number", number);
        model.addAttribute("isEven", isEven);
        return "evenNumberResult";
    }
}