package ru.job4j.design.menu;

import java.util.*;

public class ActionDown implements UserAction {
    @Override
    public String getName() {
        return "Down";
    }

    @Override
    public boolean action(Toolbox toolbox, Scanner scanner) {
        String current = toolbox.getCurrentNumber();
        List<String> list = new ArrayList<>(toolbox.getMapItems().keySet());
        if (current.equals(list.get(list.size() - 1))) {
            toolbox.setCurrentNumber(list.get(0));
            return true;
        }
        Iterator<String> iterator = new ArrayList<>(list).iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.equals(current)) {
                toolbox.setCurrentNumber(iterator.next());
                return true;
            }
        }
        throw new RuntimeException("The current item was not found");
    }
}
