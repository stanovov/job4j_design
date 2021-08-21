package ru.job4j.design.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenCreatingParkingWithSpaceOnlyForTrucks() {
        new Parking(0, 1);
    }

    @Test
    public void whenCreatingParkingWithSpaceOnlyForPassengerCars() {
        new Parking(1, 0);
    }

    @Test
    public void whenCreatingParkingWithSpaceForAllCars() {
        new Parking(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParkingWithNoSpacesIsCreated() {
        new Parking(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParkingWithNegativeParameterPlacesForPassengersCarsIsCreated() {
        new Parking(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParkingWithNegativeParameterPlacesForTrucksIsCreated() {
        new Parking(2, -5);
    }

    @Test
    public void whenPassengerCarsPark() {
        ParkingLot parkingLot = new Parking(3, 0);
        PassengerCar car1 = new PassengerCar("1");
        PassengerCar car2 = new PassengerCar("2");
        PassengerCar car3 = new PassengerCar("3");
        assertTrue(parkingLot.park(car1));
        assertTrue(parkingLot.park(car2));
        assertTrue(parkingLot.park(car3));
    }

    @Test
    public void whenPassengerCarsParkAndNotEnoughSpace() {
        ParkingLot parkingLot = new Parking(2, 0);
        PassengerCar car1 = new PassengerCar("1");
        PassengerCar car2 = new PassengerCar("2");
        PassengerCar car3 = new PassengerCar("3");
        assertTrue(parkingLot.park(car1));
        assertTrue(parkingLot.park(car2));
        assertFalse(parkingLot.park(car3));
    }

    @Test
    public void whenParkingIsOnlyForTruck() {
        ParkingLot parkingLot = new Parking(0, 1);
        PassengerCar passengerCar = new PassengerCar("1");
        Truck truck = new Truck("2", 2);
        assertFalse(parkingLot.park(passengerCar));
        assertTrue(parkingLot.park(truck));
    }

    @Test
    public void whenTruckIsParkedInPlaceForPassengerCar() {
        ParkingLot parkingLot = new Parking(2, 0);
        Truck truck = new Truck("1", 2);
        assertTrue(parkingLot.park(truck));
    }

    @Test
    public void whenFirstTruckParkedInPlaceForTruckThenCarIsParkedThenTruckParkedInPlaceForThePassengerCars() {
        ParkingLot parkingLot = new Parking(4, 1);
        Truck firstTruck = new Truck("1", 4);
        PassengerCar passengerCar = new PassengerCar("2");
        Truck secondTruck = new Truck("3", 3);
        assertTrue(parkingLot.park(firstTruck));
        assertTrue(parkingLot.park(passengerCar));
        assertTrue(parkingLot.park(secondTruck));
    }

    @Test
    public void whenTruckRanOutOfSpace() {
        ParkingLot parkingLot = new Parking(2, 0);
        Truck truck = new Truck("1", 3);
        assertFalse(parkingLot.park(truck));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSamePassengerCarIsParked() {
        ParkingLot parkingLot = new Parking(2, 0);
        PassengerCar car = new PassengerCar("1");
        parkingLot.park(car);
        parkingLot.park(car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSameTruckIsParked() {
        ParkingLot parkingLot = new Parking(0, 2);
        CanPark truck = new Truck("1", 1);
        parkingLot.park(truck);
        parkingLot.park(truck);
    }
}