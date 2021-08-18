package ru.job4j.ood.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Supplier;

public class Painter {

    public void draw(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    public void save(Path path, Supplier<String> supplier) throws IOException {
        Files.writeString(path, supplier.get(), StandardOpenOption.CREATE);
    }

}
