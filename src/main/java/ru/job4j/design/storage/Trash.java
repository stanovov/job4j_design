package ru.job4j.design.storage;

public class Trash extends Storage {
    @Override
    public boolean add(Food food) {
        if (food.getPercentageSpent() >= 100) {
            return foods.add(food);
        }
        return false;
    }
}
