package ru.app.devices;

import ru.app.os.OS;

public class Laptop extends Device {
    public Laptop(OS os) {
        super(os);
    }

    @Override
    public void info() {
        System.out.print("Ноутбук с ");
        os.boot();
    }
}
