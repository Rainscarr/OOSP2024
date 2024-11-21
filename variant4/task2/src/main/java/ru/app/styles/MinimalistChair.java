package ru.app.styles;

import ru.app.furniture.Chair;

public class MinimalistChair implements Chair {
    @Override
    public void create() {
        System.out.println("Минималистичный стул");
    }
}
