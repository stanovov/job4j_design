package ru.job4j.design.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prompt implements Item {

    private final String name;

    private final String description;

    private final List<Item> childItems;

    public Prompt(String name, String description) {
        this(name, description, new ArrayList<>());
    }

    public Prompt(String name, String description, List<Item> childItems) {
        this.name = name;
        this.description = description;
        this.childItems = childItems;
    }

    @Override
    public List<Item> childItems() {
        return childItems;
    }

    @Override
    public String getName() {
        return name.isEmpty() ? "Prompt" : "Prompt \"" + name + "\"";
    }

    @Override
    public boolean action(Toolbox toolbox, Scanner scanner) {
        System.out.printf("Prompt: %s%n", description);
        return true;
    }
}
