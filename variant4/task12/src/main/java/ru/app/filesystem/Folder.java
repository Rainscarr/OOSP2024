package ru.app.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemComponent {
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void add(FileSystemComponent component) {
        components.add(component);
    }

    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Папка: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}
