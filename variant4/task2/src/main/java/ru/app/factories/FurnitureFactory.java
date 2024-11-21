package ru.app.factories;

import ru.app.furniture.Chair;
import ru.app.furniture.Table;

public interface FurnitureFactory {
    Chair createChair();
    Table createTable();
}
