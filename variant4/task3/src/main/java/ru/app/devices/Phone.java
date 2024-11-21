package ru.app.devices;

import ru.app.os.OS;

public class Phone extends Device {
    public Phone(OS os) {
        super(os);
    }

    @Override
    public void info() {
        System.out.print("Смартфон с ");
        os.boot();
    }
}
