package ru.job4j.design.parking;

public class PassengerCar implements CanPark {

    public PassengerCar(String carNumber) {
    }

    @Override
    public int getOccupiedSpace() {
        return 0;
    }
}
