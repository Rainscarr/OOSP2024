package ru.app.styles;

import ru.app.furniture.Chair;

public class ModernChair implements Chair {
    @Override
    public void create() {
        System.out.println("Современный стул");
    }
}
