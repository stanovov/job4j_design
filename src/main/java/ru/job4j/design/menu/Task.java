package ru.job4j.design.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task implements Item {

    private final String name;

    private final String description;

    private final String correctAnswer;

    private final List<Item> childItems;

    public Task(String name, String description, String correctAnswer) {
        this(name, description, correctAnswer, new ArrayList<>());
    }

    public Task(String name, String description, String correctAnswer, List<Item> childItems) {
        this.name = name;
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.childItems = new ArrayList<>(childItems);
    }

    @Override
    public List<Item> childItems() {
        return childItems;
    }

    @Override
    public String getName() {
        return name.isEmpty() ? "Task" : "Task \"" + name + "\"";
    }

    @Override
    public boolean action(Toolbox toolbox, Scanner scanner) {
        System.out.println(description);
        String result = scanner.nextLine();
        if (result.equals(correctAnswer)) {
            System.out.println("Correct answer!");
        } else {
            System.out.println("Wrong answer!");
        }
        return true;
    }
}
