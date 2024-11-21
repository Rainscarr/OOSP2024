package ru.app.orders;

public class ClothesOrder extends Order {
    @Override
    protected void confirmOrder() {
        System.out.println("Подтверждение заказа одежды");
    }

    @Override
    protected void packOrder() {
        System.out.println("Упаковка одежды");
    }

    @Override
    protected void deliverOrder() {
        System.out.println("Доставка одежды");
    }
}
