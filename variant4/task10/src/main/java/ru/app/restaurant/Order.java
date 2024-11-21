package ru.app.restaurant;

public class Order {
    private String item;
    private int price;

    public void placeOrder(String item, int price) {
        this.item = item;
        this.price = price;
        System.out.println("Заказ оформлен: " + item + " за " + price + " руб");
    }

    public int getPrice() {
        return price;
    }
}
