package ru.job4j.design.parking;

public interface ParkingLot {
    boolean park(CanPark car);
    boolean contains(CanPark canPark);
}
