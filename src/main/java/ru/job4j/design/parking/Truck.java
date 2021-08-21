package ru.job4j.design.parking;

public class Truck implements CanPark {

    public Truck(String carNumber, int occupiedSpace) {
    }

    @Override
    public int getOccupiedSpace() {
        return 0;
    }
}
