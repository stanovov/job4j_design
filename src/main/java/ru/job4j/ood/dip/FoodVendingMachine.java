package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodVendingMachine {

    private Map<String, List<Food>> store = new HashMap<>();

    public boolean addFood(String key, Food food) {
        return false;
    }

    public Food getFood(String key) {
        return null;
    }

    public Food buy(String key, double money) {
        Food food = getFood(key);
        if (food.getPrice() <= money) {
            return food;
        }
        throw new IllegalArgumentException();
    }
}
