package ru.job4j.ood.dip;

import java.util.List;

public class Track {

    private List<Car> cars;

    public Track(List<Car> cars) {
        this.cars = cars;
    }

    public Car startRace() {
        return cars.get(0);
    }
}
