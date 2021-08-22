package ru.job4j.design.parking;

import java.util.Objects;

public class PassengerCar implements CanPark {

    private String carNumber;

    private final int occupiedSpace = 1;

    public PassengerCar(String carNumber) {
        this.carNumber = carNumber;
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
        PassengerCar car = (PassengerCar) o;
        return Objects.equals(carNumber, car.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, occupiedSpace);
    }
}
