package ru.job4j.design.parking;

import java.util.Objects;

public class Truck implements CanPark {

    private String carNumber;

    private int occupiedSpace;

    public Truck(String carNumber, int occupiedSpace) {
        validate(occupiedSpace);
        this.carNumber = carNumber;
        this.occupiedSpace = occupiedSpace;
    }

    private void validate(int occupiedSpace) {
        if (occupiedSpace < 2) {
            throw new IllegalArgumentException("The truck cannot take up less space");
        }
    }

    @Override
    public int getOccupiedSpace() {
        return occupiedSpace;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return occupiedSpace == truck.occupiedSpace
                && Objects.equals(carNumber, truck.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, occupiedSpace);
    }
}
