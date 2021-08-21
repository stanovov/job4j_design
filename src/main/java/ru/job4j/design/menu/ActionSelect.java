package ru.job4j.design.menu;

import java.util.Scanner;

public class ActionSelect implements UserAction {
    @Override
    public String getName() {
        return "Select";
    }

    @Override
    public boolean action(Toolbox toolbox, Scanner scanner) {
        Item item = toolbox.getMapItems().get(toolbox.getCurrentNumber());
        System.out.printf("You selected: \"%s\"%n", item.getName());
        return item.action(toolbox, scanner);
    }
}
