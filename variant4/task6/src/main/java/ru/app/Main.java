package ru.app;

import ru.app.orders.ClothesOrder;
import ru.app.orders.ElectronicsOrder;

public class Main {
    public static void main(String[] args) {
        ElectronicsOrder electronicsOrder = new ElectronicsOrder();
        electronicsOrder.processOrder();

        ClothesOrder clothesOrder = new ClothesOrder();
        clothesOrder.processOrder();
    }
}
