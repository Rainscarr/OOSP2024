package ru.app.devices;

import ru.app.os.OS;

public class Tablet extends Device {
    public Tablet(OS os) {
        super(os);
    }

    @Override
    public void info() {
        System.out.print("Планшет с ");
        os.boot();
    }
}
