package ru.app.devices;

import ru.app.os.OS;

public abstract class Device {
    protected OS os;

    public Device(OS os) {
        this.os = os;
    }

    public abstract void info();
}
