package ru.job4j.ood.dip;

import java.time.LocalDate;

public class AnnualReport {

    private PostgreSQLDatabase database = new PostgreSQLDatabase();

    public void open(LocalDate date) {

    }

    public boolean save() {
        return false;
    }
}
