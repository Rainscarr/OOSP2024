package ru.app.filesystem;

public class File extends FileSystemComponent {
    public File(String name) {
        super(name);
    }

    @Override
    public void showDetails() {
        System.out.println("Файл: " + name);
    }
}
