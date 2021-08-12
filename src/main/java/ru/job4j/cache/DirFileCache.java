package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
        try {
            Files.list(Path.of(cachingDir))
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> load(path.toFile().getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String load(String key) {
        try (BufferedReader in = new BufferedReader(
                new FileReader(cachingDir + File.separator + key))) {
            StringBuilder sb = new StringBuilder();
            in.lines().forEach(s -> sb.append(s).append(System.lineSeparator()));
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
