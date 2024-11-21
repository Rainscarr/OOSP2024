package ru.app;

import ru.app.devices.Device;
import ru.app.devices.Phone;
import ru.app.devices.Tablet;
import ru.app.devices.Laptop;
import ru.app.os.Android;
import ru.app.os.iOS;
import ru.app.os.Windows;

public class Main {
    public static void main(String[] args) {
        Device phone = new Phone(new Android());
        phone.info();

        Device tablet = new Tablet(new iOS());
        tablet.info();

        Device laptop = new Laptop(new Windows());
        laptop.info();
    }
}
