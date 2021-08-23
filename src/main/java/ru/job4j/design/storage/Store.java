package ru.job4j.design.storage;

import java.util.List;

public interface Store {
    boolean add(Food food);
    List<Food> getAll();
    int size();
    void clear();
}
