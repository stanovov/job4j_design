package ru.job4j.io;

import java.io.File;

public class Dir {
    private static final int GB = 1073741824;
    private static final int KB = 1024;

    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("Total disk size : %s Gb%n", file.getTotalSpace() / GB);
        for (File subfile : file.listFiles()) {
            if (subfile.isFile()) {
                System.out.println(subfile.getName() + " : " + subfile.length() / KB + " kb");
            } else {
                System.out.println(subfile.getName() + " : " + getBytes(subfile) / KB + " kb");
            }
        }
    }

    private static int getBytes(File dir) {
        int result = 0;
        for (File subfile : dir.listFiles()) {
            result += subfile.isFile() ? subfile.length() : getBytes(subfile);
        }
        return result;
    }
}