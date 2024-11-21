package ru.app;

import ru.app.factories.CarFactory;
import ru.app.factories.BikeFactory;
import ru.app.factories.MotorcycleFactory;
import ru.app.factories.TransportFactory;
import ru.app.transport.Transport;

public class Main {
    public static void main(String[] args) {
        TransportFactory carFactory = new CarFactory();
        Transport car = carFactory.createTransport();
        car.drive();

        TransportFactory bikeFactory = new BikeFactory();
        Transport bike = bikeFactory.createTransport();
        bike.drive();

        TransportFactory motorcycleFactory = new MotorcycleFactory();
        Transport motorcycle = motorcycleFactory.createTransport();
        motorcycle.drive();
    }
}
