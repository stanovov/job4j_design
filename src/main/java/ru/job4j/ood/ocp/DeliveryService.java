package ru.job4j.ood.ocp;

public class DeliveryService {

    public double calculateTheCost(Coordinates from, Coordinates to, String type) {
        double result = 0;
        double factor = 1;
        if (type.equals("road")) {
            factor = 0.9;
            // for a car your own calculation
        } else if (type.equals("ship")) {
            factor = 1.2;
            // for a ship your own calculation
        } else if (type.equals("plane")) {
            factor = 1.5;
            // for a plane your own calculation
        }
        return (double) Math.round(result * 100) / 100;
    }
}
