package ru.app;

import ru.app.restaurant.RestaurantFacade;

public class Main {
    public static void main(String[] args) {
        RestaurantFacade restaurant = new RestaurantFacade();

        restaurant.showMenu();
        restaurant.placeOrder("Пицца");
        restaurant.payOrder(500);
    }
}
