package ru.job4j.design.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TruckTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateTruckWithNegativeOccupiedSpace() {
        new Truck("1", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateTruckWithZeroOccupiedSpace() {
        new Truck("1", 0);
    }

    @Test
    public void whenCreateTruckWithPositiveOccupiedSpace() {
        new Truck("1", 2);
    }

    @Test
    public void whenGetOccupiedSpaceByTruck() {
        Truck truck = new Truck("1", 2);
        int expect = 2;
        int result = truck.getOccupiedSpace();
        assertThat(expect, is(result));
    }
}