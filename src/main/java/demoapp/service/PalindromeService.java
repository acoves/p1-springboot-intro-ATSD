package demoapp.service;

import org.springframework.stereotype.Service;

@Service
public class PalindromeService {
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        String cleaned = word.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
}