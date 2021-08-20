package ru.job4j.design.storage;

import java.time.LocalDate;

public class Groats extends Food {
    public Groats(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
