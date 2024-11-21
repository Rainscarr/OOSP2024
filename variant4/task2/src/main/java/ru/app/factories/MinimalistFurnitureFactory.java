package ru.app.factories;

import ru.app.styles.MinimalistChair;
import ru.app.styles.MinimalistTable;
import ru.app.furniture.Chair;
import ru.app.furniture.Table;

public class MinimalistFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new MinimalistChair();
    }

    @Override
    public Table createTable() {
        return new MinimalistTable();
    }
}
