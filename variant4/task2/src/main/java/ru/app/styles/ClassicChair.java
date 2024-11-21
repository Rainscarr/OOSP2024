package ru.app.styles;

import ru.app.furniture.Chair;

public class ClassicChair implements Chair {
    @Override
    public void create() {
        System.out.println("Классический стул");
    }
}
