package ru.app;

import ru.app.filesystem.Folder;
import ru.app.filesystem.File;

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("Корневая папка");
        Folder subFolder1 = new Folder("Папка 1");
        Folder subFolder2 = new Folder("Папка 2");

        File file1 = new File("Файл 1.txt");
        File file2 = new File("Файл 2.txt");

        subFolder1.add(file1);
        subFolder2.add(file2);

        root.add(subFolder1);
        root.add(subFolder2);

        root.showDetails();
    }
}
