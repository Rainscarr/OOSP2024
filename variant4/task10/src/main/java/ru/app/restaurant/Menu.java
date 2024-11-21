package ru.app.restaurant;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Integer> items = new HashMap<>();

    public Menu() {
        items.put("Пицца", 500);
        items.put("Салат", 300);
        items.put("Напиток", 100);
    }

    public void showMenu() {
        System.out.println("Меню:");
        items.forEach((item, price) -> System.out.println(item + " - " + price + " руб"));
    }

    public boolean hasItem(String item) {
        return items.containsKey(item);
    }

    public int getPrice(String item) {
        return items.getOrDefault(item, 0);
    }
}
