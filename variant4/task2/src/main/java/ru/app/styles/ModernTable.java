package ru.app.styles;

import ru.app.furniture.Table;

public class ModernTable implements Table {
    @Override
    public void create() {
        System.out.println("Современный стол");
    }
}
