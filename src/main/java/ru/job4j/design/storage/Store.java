package ru.job4j.design.storage;

public interface Store {
    boolean add(Food food);
    int size();
}
