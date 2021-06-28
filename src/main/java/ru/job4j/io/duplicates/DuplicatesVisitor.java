package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> files = new HashMap<>();
    private Map<FileProperty, List<Path>> duplicates = new LinkedHashMap<>();

    public void show() {
        for (FileProperty key : duplicates.keySet()) {
            List<Path> paths = duplicates.get(key);
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
            files.put(property, file);
        } else {
            if (duplicates.containsKey(property)) {
                duplicates.get(property)
                        .add(file);
            } else {
                List<Path> paths = new ArrayList<>();
                paths.add(files.get(property));
                paths.add(file);
                duplicates.put(property, paths);
            }
        }
        return super.visitFile(file, attrs);
    }
}