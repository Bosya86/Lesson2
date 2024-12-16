package org.example;

import java.util.Arrays;

public class ArrayProcessor {
    public static void main(String[] args) {
        String[][] correctArray = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}};

        try {
            System.out.println("Сумма элементов массива: " + processArray(correctArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        // Пример неправильного массива (размер 3x4)
        String[][] wrongSizedArray = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}};

        try {
            System.out.println("Сумма элементов массива: " + processArray(wrongSizedArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        // Пример массива с неверными данными
        String[][] incorrectDataArray = {{"1", "2", "3", "4"},
                {"5", "6", "a", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}};

        try {
            System.out.println("Сумма элементов массива: " + processArray(incorrectDataArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }
    }

    private static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || Arrays.stream(array).anyMatch(row -> row.length != 4)) {
            throw new MyArraySizeException("Размер массива должен быть 4x4.");
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка преобразования данных в ячейке [" + i + ", " + j + "]");
                }
            }
        }

        return sum;
    }
}
