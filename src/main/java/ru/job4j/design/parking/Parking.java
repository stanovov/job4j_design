package ru.job4j.design.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkingLot, StoragePassengerCars, StorageTrucks {

    private final List<CanPark> cars = new ArrayList<>();

    private final int capacityPassengerCars;

    private final int capacityTrucks;

    private int sizePassengerCars = 0;

    private int sizeTrucks = 0;

    public Parking(int capacityPassengerCars, int capacityTrucks) {
        validate(capacityPassengerCars, capacityTrucks);
        this.capacityPassengerCars = capacityPassengerCars;
        this.capacityTrucks = capacityTrucks;
    }

    private void validate(int capacityPassengerCars, int capacityTrucks) {
        if (capacityPassengerCars < 0 || capacityTrucks < 0) {
            throw new IllegalArgumentException("There can be no negative capacity!");
        }
        if (capacityPassengerCars == 0 && capacityTrucks == 0) {
            throw new IllegalArgumentException("Parking cannot be without places");
        }
    }

    @Override
    public int getCapacityPassengerCars() {
        return capacityPassengerCars;
    }

    @Override
    public int getSizePassengerCars() {
        return sizePassengerCars;
    }

    @Override
    public int getCapacityTrucks() {
        return capacityTrucks;
    }

    @Override
    public int getSizeTrucks() {
        return sizeTrucks;
    }

    @Override
    public boolean park(CanPark car) {
        if (contains(car)) {
            throw new IllegalArgumentException("This car is already parked");
        }
        int occupiedSpace = car.getOccupiedSpace();
        if (occupiedSpace < 1) {
            throw new IllegalArgumentException("Car has invalid occupied space");
        }
        boolean result = false;
        if (occupiedSpace == 1) {
            if ((sizePassengerCars + 1) <= capacityPassengerCars) {
                sizePassengerCars++;
                result =  cars.add(car);
            }
        } else {
            if ((sizeTrucks + 1) <= capacityTrucks) {
                sizeTrucks++;
                result = cars.add(car);
            } else if ((sizePassengerCars + occupiedSpace) <= capacityPassengerCars) {
                sizePassengerCars += occupiedSpace;
                result = cars.add(car);
            }
        }
        return result;
    }

    @Override
    public boolean contains(CanPark car) {
        return cars.contains(car);
    }
}
