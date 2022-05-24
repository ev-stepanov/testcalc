package com.example.calc.service;

import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public int add(int number1, int number2) {

        return number1 + number2;
    }
}
