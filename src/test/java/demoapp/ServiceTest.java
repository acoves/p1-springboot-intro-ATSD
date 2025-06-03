package demoapp;


import demoapp.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class ServiceTest {

    @Autowired
    SaludoService saludo;
    PalindromeService palindromeService = new PalindromeService(); // PalindromeService is a service

    @Test
    public void contexLoads() throws Exception {
        assertThat(saludo).isNotNull();
    }

    @Test
    public void serviceSaludo() throws Exception {
        assertThat(saludo.saluda("Juan")).isEqualTo("Hola Juan");
    }

    @Test //PalindromeService is a service
    public void testIsPalindrome() {
        assertTrue(palindromeService.isPalindrome("madam"));
        assertFalse(palindromeService.isPalindrome("hello"));
        assertTrue(palindromeService.isPalindrome("A man a plan a canal Panama"));
        assertFalse(palindromeService.isPalindrome(null));
    }
}