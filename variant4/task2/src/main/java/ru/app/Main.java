package ru.app;

import ru.app.factories.FurnitureFactory;
import ru.app.factories.ClassicFurnitureFactory;
import ru.app.factories.ModernFurnitureFactory;
import ru.app.factories.MinimalistFurnitureFactory;
import ru.app.furniture.Chair;
import ru.app.furniture.Table;

public class Main {
    public static void main(String[] args) {
        FurnitureFactory classicFactory = new ClassicFurnitureFactory();
        Chair classicChair = classicFactory.createChair();
        Table classicTable = classicFactory.createTable();
        classicChair.create();
        classicTable.create();

        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Table modernTable = modernFactory.createTable();
        modernChair.create();
        modernTable.create();

        FurnitureFactory minimalistFactory = new MinimalistFurnitureFactory();
        Chair minimalistChair = minimalistFactory.createChair();
        Table minimalistTable = minimalistFactory.createTable();
        minimalistChair.create();
        minimalistTable.create();
    }
}
