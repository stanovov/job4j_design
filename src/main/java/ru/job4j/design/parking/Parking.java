package ru.job4j.design.parking;

public class Parking implements ParkingLot {

    public Parking(int placesForPassengersCars, int placesForTrucks) {
    }

    @Override
    public boolean park(CanPark car) {
        return false;
    }
}
