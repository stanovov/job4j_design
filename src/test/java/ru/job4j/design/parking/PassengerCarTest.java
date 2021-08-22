package ru.job4j.design.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PassengerCarTest {

    @Test
    public void whenCreatePassengerCar() {
        new PassengerCar("1");
    }

    @Test
    public void whenGetOccupiedSpaceByPassengerCar() {
        CanPark car = new PassengerCar("1");
        int expect = 1;
        int result = car.getOccupiedSpace();
        assertThat(expect, is(result));
    }
}