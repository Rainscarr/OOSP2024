package ru.app.factories;

import ru.app.styles.ModernChair;
import ru.app.styles.ModernTable;
import ru.app.furniture.Chair;
import ru.app.furniture.Table;

public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }
}
