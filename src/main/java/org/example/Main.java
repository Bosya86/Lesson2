package org.example;

public class Main {
    public static void main(String[] args) {

        Bowl bowl = new Bowl(100);


        Cat[] cats = new Cat[3];
        cats[0] = new Cat();
        cats[1] = new Cat();
        cats[2] = new Cat();


        Dog dog = new Dog();


        for (Cat cat : cats) {
            cat.eatFromBowl(bowl);
        }


        for (int i = 0; i < cats.length; i++) {
            Cat cat = cats[i];
            String status = cat.isHungry() ? "голоден" : "сыт";
            System.out.println("Кот №" + (i + 1) + " сейчас " + status);
        }


        bowl.addFood(40);


        for (Cat cat : cats) {
            if (cat.isHungry()) {
                cat.eatFromBowl(bowl);
            }
        }


        for (int i = 0; i < cats.length; i++) {
            Cat cat = cats[i];
            String status = cat.isHungry() ? "голоден" : "сыт";
            System.out.println("Кот №" + (i + 1) + " сейчас " + status);
        }


        System.out.println("Сейчас в миске осталось " + bowl.getCurrentFoodAmount() + " еды.");


        System.out.println("Общее количество животных: " + Animal.getAnimalCount());
        System.out.println("Количество собак: " + Dog.getDogCount());
        System.out.println("Количество котов: " + Cat.getCatCount());
    }
}
