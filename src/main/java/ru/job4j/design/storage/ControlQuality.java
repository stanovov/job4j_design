package ru.job4j.design.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void resort() {
        List<Food> foods = stores.stream()
                .flatMap(store -> store.getAll().stream())
                .collect(Collectors.toList());
        stores.forEach(Store::clear);
        redistribute(foods);
    }
}
