package ru.job4j.design.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void redistribute(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

    public void redistribute(List<Food> foods) {
        for (Food food : foods) {
            redistribute(food);
        }
    }
}
