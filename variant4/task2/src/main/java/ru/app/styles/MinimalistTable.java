package ru.app.styles;

import ru.app.furniture.Table;

public class MinimalistTable implements Table {
    @Override
    public void create() {
        System.out.println("Минималистичный стол");
    }
}
