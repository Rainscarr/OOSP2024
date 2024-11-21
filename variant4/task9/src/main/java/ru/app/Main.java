package ru.app;

import ru.app.settings.AppSettings;

public class Main {
    public static void main(String[] args) {
        AppSettings settings1 = AppSettings.getInstance();
        settings1.setOption("Тема", "Темная");

        AppSettings settings2 = AppSettings.getInstance();
        System.out.println("Текущая тема: " + settings2.getOption("Тема"));

        settings2.setOption("Язык", "Русский");
        System.out.println("Текущий язык: " + settings1.getOption("Язык"));
    }
}
