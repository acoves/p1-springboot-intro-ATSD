package demoapp.service;
import org.springframework.stereotype.Service;

@Service
public class SquareService {

    public boolean isSquare(int number1, int number2) {
        return number2 == number1 * number1;
    }
}