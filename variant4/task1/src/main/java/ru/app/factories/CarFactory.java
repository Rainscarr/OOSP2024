package ru.app.factories;

import ru.app.transport.Car;
import ru.app.transport.Transport;

public class CarFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new Car();
    }
}
