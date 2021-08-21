package ru.job4j.design.menu;

import java.util.Scanner;

public interface UserAction {
    String getName();
    boolean action(Toolbox toolbox, Scanner scanner);
}
