package demoapp.service;

import org.springframework.stereotype.Service;

@Service
public class EvenNumberService {

    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}