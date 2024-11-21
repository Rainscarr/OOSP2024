package ru.app.factories;

import ru.app.transport.Motorcycle;
import ru.app.transport.Transport;

public class MotorcycleFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new Motorcycle();
    }
}
