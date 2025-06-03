package demoapp;


import demoapp.service.PalindromeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    // Hacemos una petición GET a un end point y comprobamos que
    // el HTML resultante es correcto
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    // Podemos hacer también una petición POST y pasar los datos
    // del formulario con el método .param
    @Test
    public void postShoudReturnCorrectResponse() throws Exception {
        this.mockMvc.perform(post("/saludoform")
                        .param("nombre", "Juan"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola Juan")));
    }

    @MockBean
    private PalindromeService palindromeService;

    @Test
    public void testCheckPalindrome() throws Exception {
        when(palindromeService.isPalindrome("madam")).thenReturn(true);

        mockMvc.perform(post("/palindrome").param("word", "madam"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("isPalindrome", true))
                .andExpect(view().name("palindromeResult"));
    }
}