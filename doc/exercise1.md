# Exercise 1 - Spring Boot Application

## 📌 Overview
This project is a **Spring Boot** application developed as part of the Agile Techniques course. It is designed to provide a set of basic functionalities through a web interface, following the **Model-View-Controller (MVC) architecture**. The application is containerized using **Docker** and includes automated testing to ensure functionality and reliability.

## 🚀 Implemented Functionalities
The application includes the following features:

### **Palindrome Checker**
Allows users to input a word or phrase and determines whether it is a palindrome, ignoring spaces and case sensitivity.
- **Controller:** Handles HTTP requests and forwards user input to the service layer.
- **Service:** Implements logic to strip spaces and check if the string is identical when reversed.
- **Thymeleaf View:** Displays input and results.

### **Even Number Checker**
Determines if an entered number is even or odd.
- **Controller:** Processes requests and passes the number to the service layer.
- **Service:** Uses simple modulus operation to determine if the number is even.
- **Thymeleaf View:** Displays the result to the user.

### **Basic Calculator**
Performs basic arithmetic operations including addition, subtraction, multiplication, and division.
- **Controller:** Accepts numerical inputs and the desired operation.
- **Service:** Implements logic for mathematical calculations and handles division by zero cases.
- **Thymeleaf View:** Provides an interactive form for users to enter numbers and select operations.

### **Square Number Checker**
Checks if one number is the square of another.
- **Controller:** Receives two numbers and passes them to the service.
- **Service:** Computes if the first number squared equals the second number.
- **Thymeleaf View:** Displays the result and allows user re-entry.

---

## 🛠️ Implementation Details
The project was implemented using:
- **Spring Boot:** Framework for Java web applications.
- **Thymeleaf:** Template engine for rendering views.
- **Maven:** Dependency management and build automation tool.
- **JUnit & MockMvc:** Automated testing tools.
- **Docker:** Containerization for easy deployment.

Each functionality follows a structured approach:
- **Controller Layer:** Manages HTTP requests and directs traffic.
- **Service Layer:** Contains the core logic and computations.
- **View Layer:** Uses Thymeleaf templates to present results.

---

## ✅ Testing

The project includes **unit tests and integration tests** to verify correctness and stability.

### 🔹 **Unit Tests**

* **Service Layer Tests:** Validate that each service method functions correctly.
* **JUnit Assertions:** Verify expected versus actual outcomes.

### 🔹 **Integration Tests**

* **MockMvc Tests:** Simulate HTTP requests and verify responses.
* **Form Input Handling:** Ensure correct parsing of user input.

The application includes an extensive suite of **automated tests**, divided into **unit tests** (service logic) and **integration tests** (web controllers). These tests ensure the application's correctness, robustness, and stability across all layers.

---

### 🔬 Unit Tests (`ServiceTest.java`)

These tests validate the internal logic of the application's service layer. Each test is focused on ensuring that the methods behave as expected when given correct inputs and handle edge cases gracefully.

* **SaludoService:**
  Verifies that the service returns a greeting string correctly formatted with the user's name.

  ```java
  assertThat(saludo.saluda("Juan")).isEqualTo("Hola Juan");
  ```

  ✅ Should return "Hola Juan".
  ❌ Would fail if the prefix or name formatting is incorrect.

* **PalindromeService:**
  Checks whether a given word or phrase is a palindrome. Spaces and case are ignored.

  ```java
  assertTrue(palindromeService.isPalindrome("madam"));
  assertTrue(palindromeService.isPalindrome("A man a plan a canal Panama"));
  assertFalse(palindromeService.isPalindrome("hello"));
  assertFalse(palindromeService.isPalindrome(null));
  ```

  ✅ Should detect palindromes correctly, including phrases with spaces.
  ❌ Should return false for non-palindromes and handle null input safely.

* **CalculatorService:**
  Validates the correct result for basic arithmetic operations and checks error handling for invalid operations.

  ```java
  assertEquals(5, calculatorService.calculate(2, 3, "add"));
  assertEquals(-1, calculatorService.calculate(2, 3, "subtract"));
  assertEquals(6, calculatorService.calculate(2, 3, "multiply"));
  assertEquals(2, calculatorService.calculate(6, 3, "divide"));
  assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(6, 0, "divide"));
  assertThrows(IllegalArgumentException.class, () -> calculatorService.calculate(6, 3, "invalid"));
  ```

  ✅ Should return correct result for valid operations.
  ❌ Should throw exceptions on division by zero and unknown operations.

* **EvenNumberService:**
  Determines whether a number is even.

  ```java
  assertTrue(evenNumberService.isEven(2));
  assertFalse(evenNumberService.isEven(3));
  ```

  ✅ Should return true for even numbers.
  ❌ Should return false for odd numbers.

* **SquareService:**
  Checks if the second number is the square of the first.

  ```java
  assertTrue(squareService.isSquare(2, 4));
  assertFalse(squareService.isSquare(2, 5));
  ```

  ✅ Should return true if second is exact square of the first.
  ❌ Should return false if not.

---

### 🌐 Integration Tests (`MockMvcTest.java`)

These tests simulate actual HTTP requests to verify that the controllers handle input correctly, delegate logic to services properly, and return the right views and model attributes. Services are mocked to isolate the controller logic.

* **Homepage Test:**

  ```java
  this.mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("Hello World")));
  ```

  ✅ Should return status 200 and contain "Hello World" in the HTML.

* **Greeting Form Test:**

  ```java
  this.mockMvc.perform(post("/saludoform").param("nombre", "Juan"))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("Hola Juan")));
  ```

  ✅ Should display the greeting with user's name.
  ❌ Would fail if form binding or template is broken.

* **Palindrome Controller Test:**

  ```java
  when(palindromeService.isPalindrome("madam")).thenReturn(true);

  mockMvc.perform(post("/palindrome").param("word", "madam"))
      .andExpect(status().isOk())
      .andExpect(model().attribute("isPalindrome", true))
      .andExpect(view().name("palindromeResult"));
  ```

  ✅ Should show result "true" in the model.
  ❌ Would fail if controller doesn’t pass the data to the view correctly.

* **Calculator Controller Test:**

  ```java
  when(calculatorService.calculate(2, 3, "add")).thenReturn(5.0);

  mockMvc.perform(post("/calculator").param("number1", "2")
                                     .param("number2", "3")
                                     .param("operation", "add"))
      .andExpect(status().isOk())
      .andExpect(model().attribute("result", 5.0))
      .andExpect(view().name("calculatorResult"));
  ```

  ✅ Should return result 5.0 in model and render calculator result view.

* **Even Number Controller Test:**

  ```java
  when(evenNumberService.isEven(2)).thenReturn(true);

  mockMvc.perform(post("/evenNumber").param("number", "2"))
      .andExpect(status().isOk())
      .andExpect(model().attribute("isEven", true))
      .andExpect(view().name("evenNumberResult"));
  ```

  ✅ Should return true in model and show result view.

* **Square Controller Test:**

  ```java
  when(squareService.isSquare(2, 4)).thenReturn(true);

  mockMvc.perform(post("/square").param("number1", "2")
                                 .param("number2", "4"))
      .andExpect(status().isOk())
      .andExpect(model().attribute("isSquare", true))
      .andExpect(view().name("squareResult"));
  ```

  ✅ Should return true in model and load proper result view.

---

These tests ensure that both the business logic and the presentation layer of the application behave as expected under various scenarios. They provide a solid foundation for verifying changes and preventing regressions.

To run all tests:

```bash
mvn test
```


---

## 🌐 Project Repositories
- **GitHub Repository:**  
  👉 [https://github.com/acoves/p1-springboot-intro-ATSD](https://github.com/acoves/p1-springboot-intro-ATSD)

- **DockerHub Image:**  
  👉 [https://hub.docker.com/r/acoves/spring-boot-demoapp](https://hub.docker.com/r/acoves/spring-boot-demoapp)

To pull and run the Docker image:
```bash
docker pull acoves/spring-boot-demoapp:final
docker run -p 8080:8080 acoves/spring-boot-demoapp:final
```

---

## 📌 Conclusion
This project demonstrates the **use of Spring Boot with MVC architecture, automated testing with JUnit and MockMvc, and containerization with Docker**. The application is fully functional and tested, ensuring robustness and usability. The modular implementation ensures scalability and maintainability for future enhancements.

If you have any suggestions or improvements, feel free to contribute to the repository! 🚀