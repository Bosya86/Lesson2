package com.example.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FactorialCalculatorTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    void testCalculateFactorial(int number) {
        long expected = getExpectedFactorial(number);
        long actual = FactorialCalculator.calculateFactorial(number);
        Assertions.assertEquals(expected, actual);
    }

    private long getExpectedFactorial(int number) {
        switch (number) {
            case 0:
                return 1;
            case 1:
                return 1;
            case 5:
                return 120;
            case 10:
                return 3628800;
            default:
                throw new UnsupportedOperationException("Неизвестное значение для факториала");
        }
    }

    @org.junit.jupiter.api.Test
    void testCalculateFactorialForNegativeNumber() {
        int negativeNumber = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.calculateFactorial(negativeNumber);
        });
    }
}
