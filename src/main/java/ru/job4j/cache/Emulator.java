package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Emulator {

    private final DirFileCache cache;

    private final String path;

    private boolean fullLoad = false;

    public Emulator(String pathName) {
        this(new File(pathName));
    }

    public Emulator(File dir) {
        checkDir(dir);
        path = dir.toString();
        cache = new DirFileCache(path);
    }

    public void startReading() {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            if (!fullLoad) {
                loadEntireCache(console);
                fullLoad = true;
            }
            loadCache(console);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEntireCache(BufferedReader console) throws IOException {
        drawSeparator();
        System.out.println("Do you want to download the entire cache at once?");
        System.out.println("0 = \"Yes\" | Anything = \"No\"");
        String select = console.readLine();
        if (select.equals("0")) {
            Files.list(Path.of(path))
                        .filter(path -> path.toString().endsWith(".txt"))
                        .map(path -> path.toFile().getName())
                        .peek(cache::get)
                        .forEach(fileName -> System.out.printf("file cache \"%s\" loaded%n", fileName));
            System.out.println("The cache is fully loaded");
        }
    }

    private void loadCache(BufferedReader console) throws IOException {
        String select;
        boolean exit = false;
        do {
            drawSeparator();
            System.out.println("0 = \"Get file contents from cache\" "
                            + "| 1 = \"To finish work\"");
            select = console.readLine();
            if (select.equals("0")) {
                System.out.println("Enter file name:");
                String value = cache.get(console.readLine());
                System.out.println("File contents:");
                System.out.println(value);
            } else if (select.equals("1")) {
                exit = true;
            } else {
                System.out.println("Command not recognized!");
            }
        } while (!exit);
    }

    private void drawSeparator() {
        System.out.println("=".repeat(75));
    }

    private void checkDir(File dir) {
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", dir.getAbsoluteFile()));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", dir.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter directory name:");
        new Emulator(console.readLine()).startReading();
    }
}
