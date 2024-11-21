package ru.app.transport;

public class Car implements Transport {
    @Override
    public void drive() {
        System.out.println("Машина едет");
    }
}
