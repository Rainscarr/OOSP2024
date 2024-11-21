package ru.app;

import ru.app.tasks.Task;

public class Main {
    public static void main(String[] args) {
        Task originalTask = new Task("Завершить проект", "Важная задача", "2024-12-01");
        System.out.println("Оригинал: " + originalTask);

        Task clonedTask = originalTask.clone();
        clonedTask.setTitle("Сделать отчет");
        System.out.println("Клон: " + clonedTask);
    }
}
