package ru.app.orders;

public class ElectronicsOrder extends Order {
    @Override
    protected void confirmOrder() {
        System.out.println("Подтверждение заказа электроники");
    }

    @Override
    protected void packOrder() {
        System.out.println("Упаковка электроники");
    }

    @Override
    protected void deliverOrder() {
        System.out.println("Доставка электроники");
    }
}
