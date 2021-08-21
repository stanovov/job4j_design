package ru.job4j.design.menu;

import java.util.Map;

public interface Toolbox {
    Map<String, Item> getMapItems();
    String getCurrentNumber();
    void setCurrentNumber(String number);
}
