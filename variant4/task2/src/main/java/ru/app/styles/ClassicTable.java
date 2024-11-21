package ru.app.styles;

import ru.app.furniture.Table;

public class ClassicTable implements Table {
    @Override
    public void create() {
        System.out.println("Классический стол");
    }
}
