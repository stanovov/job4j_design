package ru.job4j.design.menu;

import java.util.List;

public interface Item extends UserAction {
    List<Item> childItems();
}
