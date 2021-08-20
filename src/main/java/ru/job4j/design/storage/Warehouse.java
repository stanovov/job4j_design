package ru.job4j.design.storage;

public class Warehouse extends Storage {
    @Override
    public boolean add(Food food) {
        if (food.getPercentageSpent() < 25) {
            return foods.add(food);
        }
        return false;
    }
}
