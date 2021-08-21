package ru.job4j.design.menu;

import java.util.Scanner;

public class ActionExit implements UserAction {
    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public boolean action(Toolbox toolbox, Scanner scanner) {
        return false;
    }
}
