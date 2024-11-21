package ru.app.transport;

public class Motorcycle implements Transport {
    @Override
    public void drive() {
        System.out.println("Мотоцикл едет");
    }
}
