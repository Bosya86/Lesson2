package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
// Задание 1
        String[] words = {"яблоко", "банан", "груша", "апельсин", "яблоко", "виноград", "банан", "персик", "слива", "арбуз",
                "дыня", "лимон", "мандарин", "манго", "абрикос"};

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println("Уникальные слова: " + uniqueWords);

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        System.out.println("\nКоличество вхождений каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
// Задание 2
        PhoneBook book = new PhoneBook();

        book.add("Иванов", "+7 123 456 7890");
        book.add("Петров", "+7 987 654 3210");
        book.add("Иванов", "+7 111 222 3333");

        List<String> ivanovNumbers = book.get("Иванов");
        System.out.println(ivanovNumbers);

        List<String> sidorovNumbers = book.get("Сидоров");
        System.out.println(sidorovNumbers);


    }
}

