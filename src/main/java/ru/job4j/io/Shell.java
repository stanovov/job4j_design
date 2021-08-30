package ru.job4j.io;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Shell {
    private Deque<String> deque = new ArrayDeque<>();

    public void cd(String path) {
        if (path.startsWith("..")) {
            deque.clear();
            path = path.substring(2);
        }
        if (path.startsWith("/")) {
            deque.clear();
            path = path.substring(1);
            String[] elements = path.split("/");
            deque.addAll(Arrays.asList(elements));
        } else {
            deque.offer(path);
        }
    }

    public String pwd() {
        StringBuilder sb = new StringBuilder();
        for (String element : deque) {
            sb.append("/").append(element);
        }
        return new String(sb);
    }
}