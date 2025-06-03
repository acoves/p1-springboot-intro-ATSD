package demoapp.controller;

import demoapp.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PalindromeController {

    @Autowired
    private PalindromeService palindromeService;

    @GetMapping("/palindrome")
    public String showPalindromeForm() {
        return "palindromeForm";
    }

    @PostMapping("/palindrome")
    public String checkPalindrome(@RequestParam("word") String word, Model model) {
        boolean isPalindrome = palindromeService.isPalindrome(word);
        model.addAttribute("word", word);
        model.addAttribute("isPalindrome", isPalindrome);
        return "palindromeResult";
    }
}