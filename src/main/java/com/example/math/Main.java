package com.example.math;

import com.example.math.FactorialCalculator;

public class Main {

    public static void main(String[] args) {
        int number = 5;
        long factorial = FactorialCalculator.calculateFactorial(number);
        System.out.printf("Факториал числа %d равен %d.%n", number, factorial);
    }
}
