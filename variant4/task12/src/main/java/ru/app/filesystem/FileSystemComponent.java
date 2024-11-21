package ru.app.filesystem;

public abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void showDetails();
}
