package ru.app.factories;

import ru.app.transport.Bike;
import ru.app.transport.Transport;

public class BikeFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new Bike();
    }
}
