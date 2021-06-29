package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new LinkedHashMap<>();

    public void show() {
        for (FileProperty key : files.keySet()) {
            List<Path> paths = files.get(key);
            if (paths.size() == 1) {
                continue;
            }
            System.out.printf("У файла = %s %d b имеются дубли:%n", key.getName(), key.getSize());
            for (Path path : paths) {
                System.out.println(path.toAbsolutePath());
            }
            System.out.println();
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(
                attrs.size(),
                file.getFileName().toString()
        );
        if (!files.containsKey(property)) {
            List<Path> paths = new ArrayList<>(
                    List.of(file)
            );
            files.put(property, paths);
        } else {
            files.get(property)
                    .add(file);
        }
        return super.visitFile(file, attrs);
    }
}