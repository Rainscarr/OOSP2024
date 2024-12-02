package ru.app.models;

public class Test {
    private int id;
    private String title;
    private String description;

    public Test(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title != null && !title.isEmpty() ? title : "Untitled Test (ID: " + id + ")";
    }
}
