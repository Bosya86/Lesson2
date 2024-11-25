package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
//Первое задание
        printThreeWords();
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");

//Второе задание
        checkSumSign();
    }

    public static void checkSumSign() {
        int a = 5;
        int b = -3;

        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
//Третье задание
        printColor(50);
    }

    public static void printColor(int value) {
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }

//Четвертое задание
    }

    public static void compareNumbers() {
        int a = 10;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
        //Шестое задание
        checkNumber(5);
        checkNumber(-3);
        checkNumber(0);
    }
    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
        System.out.println(isNegative(-10));  // true
        System.out.println(isNegative(15));    // false
        System.out.println(isNegative(-1));    // true
        System.out.println(isNegative(0));     // false
        System.out.println(isNegative(42));    // false
        System.out.println(isNegative(-100));  // true

        //Седьмое задание
        System.out.println(isNegative(-5)); // true
        System.out.println(isNegative(0));   // false
        System.out.println(isNegative(10));  // false
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }
    //Восьмое задание

    public static void printMultipleTimes(Object input, int times) {
        if (input instanceof String) {
            String message = (String) input;
            for (int i = 0; i < times; i++) {
                System.out.println(message);
            }
        } else if (input instanceof Number) {
            int count = ((Number) input).intValue();
            for (int i = 0; i < count; i++) {
                System.out.println(count);
            }
        } else {
            System.out.println("Unsupported type");
        }
        printMultipleTimes("Hello, World!", 3);
    }

    //Девятое задание
    public static boolean isLeapYear(int year) {

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
    //14-е задание
    public static class ArrayInitializer {

        public void main(String[] args) {
            // Пример использования метода
            int len = 5;
            int initialValue = 3;
            int[] array = createArray(len, initialValue);

            // Выводим созданный массив
            System.out.print("Созданный массив: ");
            printArray(array);
        }

        // Метод для создания массива
        public static int[] createArray(int len, int initialValue) {
            int[] array = new int[len]; // Создаем массив заданной длины
            for (int i = 0; i < len; i++) {
                array[i] = initialValue; // Заполняем массив значением initialValue
            }
            return array; // Возвращаем созданный массив
        }

        // Метод для вывода массива
        public static void printArray(int[] array) {
            for (int num : array) {
                System.out.print(num + " ");
            }
            System.out.println(); // Переход на новую строку
        }
    }


}
