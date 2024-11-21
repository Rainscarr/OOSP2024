package ru.app.orders;

public abstract class Order {
    public void processOrder() {
        confirmOrder();
        packOrder();
        deliverOrder();
    }

    protected abstract void confirmOrder();

    protected abstract void packOrder();

    protected abstract void deliverOrder();
}
