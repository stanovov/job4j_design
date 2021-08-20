package ru.job4j.design.storage;

public class Shop extends Storage {
    @Override
    public boolean add(Food food) {
        double percent = food.getPercentageSpent();
        if (percent >= 25 && percent < 100) {
            if (percent > 75) {
                food.setDiscount(
                        Math.round(food.getPrice() * 0.2 * 100.0) / 100.0
                );
            }
            return foods.add(food);
        }
        return false;
    }
}
