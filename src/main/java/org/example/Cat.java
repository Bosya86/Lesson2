package org.example;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean hungry = true;

    public Cat() {
        super();
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public void run(int distance) {
        if (!hungry && distance <= 200) {
            System.out.println("Кот пробежал " + distance + " метров.");
        } else {
            System.out.println("Кот устал после 200 метров или еще голодный.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Коты не умеют плавать.");
    }

    public void eatFromBowl(Bowl bowl) {
        if (bowl.tryEat(10)) {
            hungry = false;
            System.out.println("Кот поел и теперь сыт.");
        } else {
            System.out.println("Еды в миске недостаточно, кот остался голодным.");
        }
    }

    public boolean isHungry() {
        return hungry;
    }
}
