package ru.app.transport;

public class Bike implements Transport {
    @Override
    public void drive() {
        System.out.println("Велосипед едет");
    }
}
