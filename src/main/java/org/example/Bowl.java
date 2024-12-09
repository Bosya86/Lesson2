package org.example;

public class Bowl {
    private int foodAmount;

    public Bowl(int initialFoodAmount) {
        this.foodAmount = initialFoodAmount;
    }

    public boolean tryEat(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        foodAmount += amount;
    }

    public int getCurrentFoodAmount() {
        return foodAmount;
    }
}
