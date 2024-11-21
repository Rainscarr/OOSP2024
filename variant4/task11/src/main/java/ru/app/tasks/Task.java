package ru.app.tasks;

public class Task implements TaskPrototype {
    private String title;
    private String description;
    private String dueDate;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public Task clone() {
        return new Task(title, description, dueDate);
    }

    @Override
    public String toString() {
        return "Задача: " + title + ", Описание: " + description + ", Срок: " + dueDate;
    }
}
