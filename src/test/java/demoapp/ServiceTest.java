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
    CalculatorService calculatorService = new CalculatorService(); // CalculatorService is a service
    EvenNumberService evenNumberService = new EvenNumberService(); // EvenNumberService is a service



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


    @Test //CalculatorService is a service
    public void testCalculate() {
        assertEquals(5, calculatorService.calculate(2, 3, "add"));
        assertEquals(-1, calculatorService.calculate(2, 3, "subtract"));
        assertEquals(6, calculatorService.calculate(2, 3, "multiply"));
        assertEquals(2, calculatorService.calculate(6, 3, "divide"));
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(6, 0, "divide"));
        assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(6, 3, "invalid"));
    }

    @Test //EvenNumberService is a service
    public void testIsEven() {
        assertTrue(evenNumberService.isEven(2));
        assertFalse(evenNumberService.isEven(3));
        assertTrue(evenNumberService.isEven(0));
        assertFalse(evenNumberService.isEven(-1));
    }
}