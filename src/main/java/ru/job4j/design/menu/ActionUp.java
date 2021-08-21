package ru.job4j.design.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActionUp implements UserAction {
    @Override
    public String getName() {
        return "Up";
    }

    @Override
    public boolean action(Toolbox toolbox, Scanner scanner) {
        String currNumber = toolbox.getCurrentNumber();
        List<String> list = new ArrayList<>(toolbox.getMapItems().keySet());
        String prevNumber = list.get(list.size() - 1);
        for (String number : toolbox.getMapItems().keySet()) {
            if (number.equals(currNumber)) {
                toolbox.setCurrentNumber(prevNumber);
            }
            prevNumber = number;
        }
        return true;
    }
}
