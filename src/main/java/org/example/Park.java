package org.example;
//Задание3
import java.util.ArrayList;
import java.util.List;

public class Park {
    private String name;
    private List<Attraction> attractions;

    public Park(String name) {
        this.name = name;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(String name, String workingHours, double cost) {
        attractions.add(new Attraction(name, workingHours, cost));
    }

    public void displayAttractions() {
        System.out.println("Аттракционы в парке " + name + ":");
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }

    private class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Аттракцион: " + name + ", Время работы: " + workingHours + ", Стоимость: " + cost + " руб.";
        }
    }

    public static void main(String[] args) {
        Park amusementPark = new Park("Развлекательный Парк");
        amusementPark.addAttraction("Американские горки", "10:00 - 22:00", 500);
        amusementPark.addAttraction("Колесо обозрения", "10:00 - 21:00", 300);
        amusementPark.addAttraction("Набережная", "10:00 - 23:00", 150);

        amusementPark.displayAttractions();
    }
}

