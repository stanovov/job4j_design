package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        Path pathBase = target.getParent();
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                Path pathRelative = pathBase.relativize(source);
                zip.putNextEntry(new ZipEntry(pathRelative.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        Path pathBase = target.getParent();
        Path pathRelative = pathBase.relativize(source);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(pathRelative.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> getPredicate(String exclude) {
        Predicate<Path> predicate;
        if (exclude.isEmpty()) {
            predicate = p -> true;
        } else {
            String[] excludes = exclude.split(",");
            if (excludes.length == 1) {
                predicate = p -> !p.toFile().getName().endsWith(exclude);
            } else {
                Set<String> extensions = new HashSet<>(
                        Arrays.asList(excludes)
                );
                predicate = p -> {
                    String fileName = p.toFile().getName();
                    return extensions.stream()
                            .noneMatch(fileName::endsWith);
                };
            }
        }
        return predicate;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Incorrect number of parameters. Usage \"java -jar dir.jar "
                            + "ROOT_FOLDER EXCLUDING_EXTENSIONS ARCHIVE_NAME\"."
            );
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String output = argsName.get("o");
        String exclude = argsName.get("e");
        File file = new File(directory);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Path start = Paths.get(directory);
        List<Path> paths = Search.search(start, getPredicate(exclude));
        new Zip().packFiles(paths, Paths.get(start.getParent() + File.separator + output));
    }
}