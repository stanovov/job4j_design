package ru.job4j.design.storage;

import java.time.LocalDate;

public class Candy extends Food {
    public Candy(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
