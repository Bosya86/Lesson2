package com.example.math;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FactorialCalculatorTest {

    @DataProvider(name = "factorials")
    public Object[][] createData() {
        return new Object[][]{
                {0, 1},
                {1, 1},
                {5, 120},
                {10, 3628800}
        };
    }

    @Test(dataProvider = "factorials")
    public void testCalculateFactorial(int number, long expected) {
        long actual = FactorialCalculator.calculateFactorial(number);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCalculateFactorialForNegativeNumber() {
        int negativeNumber = -1;
        try {
            FactorialCalculator.calculateFactorial(negativeNumber);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Success
        }
    }
}
