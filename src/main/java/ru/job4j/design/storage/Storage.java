package ru.job4j.design.storage;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage implements Store {

    protected List<Food> foods = new ArrayList<>();

    @Override
    public int size() {
        return foods.size();
    }
}
