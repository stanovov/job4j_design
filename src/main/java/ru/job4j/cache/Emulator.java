package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {
    public static void main(String[] args) throws IOException {
        BufferedReader consReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter directory name:");
        File dir = new File(consReader.readLine());
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", dir.getAbsoluteFile()));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", dir.getAbsoluteFile()));
        }
        final DirFileCache dirFileCache = new DirFileCache(dir.toString());
        boolean exit = false;
        String select;
        do {
            System.out.println("=".repeat(75));
            System.out.println("0 = \"Get file contents from cache\" "
                           + "| 1 = \"To finish work\"");
            select = consReader.readLine();
            if (select.equals("0")) {
                System.out.println("Enter file name:");
                String value = dirFileCache.get(consReader.readLine());
                System.out.println("File contents:");
                System.out.println(value);
            } else if (select.equals("1")) {
                exit = true;
            } else {
                System.out.println("Command not recognized!");
            }
        } while (!exit);
    }
}
