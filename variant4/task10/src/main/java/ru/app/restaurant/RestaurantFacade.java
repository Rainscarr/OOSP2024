package ru.app.restaurant;

public class RestaurantFacade {
    private Menu menu = new Menu();
    private Order order = new Order();
    private Payment payment = new Payment();

    public void showMenu() {
        menu.showMenu();
    }

    public void placeOrder(String item) {
        if (menu.hasItem(item)) {
            order.placeOrder(item, menu.getPrice(item));
        } else {
            System.out.println("Такого блюда нет в меню");
        }
    }

    public void payOrder(int amount) {
        if (amount >= order.getPrice()) {
            payment.pay(amount);
        } else {
            System.out.println("Недостаточно средств для оплаты");
        }
    }
}
