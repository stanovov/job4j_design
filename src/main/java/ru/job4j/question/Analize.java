package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user);
        }
        int added = 0, changed = 0;
        for (User user : current) {
            int id = user.getId();
            if (previous.contains(user)) {
                previousMap.remove(id);
                continue;
            }
            if (previousMap.containsKey(id)) {
                previousMap.remove(id);
                changed++;
                continue;
            }
            added++;
        }
        return new Info(added, changed, previousMap.size());
    }

}