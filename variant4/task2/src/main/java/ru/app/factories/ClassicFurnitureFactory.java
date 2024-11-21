package ru.app.factories;

import ru.app.styles.ClassicChair;
import ru.app.styles.ClassicTable;
import ru.app.furniture.Chair;
import ru.app.furniture.Table;

public class ClassicFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ClassicChair();
    }

    @Override
    public Table createTable() {
        return new ClassicTable();
    }
}
