package ru.app.document;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private String title;
    private Map<String, String> sections = new HashMap<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSection(String name, String content) {
        sections.put(name, content);
    }

    public void show() {
        System.out.println("Заголовок: " + title);
        sections.forEach((name, content) -> {
            System.out.println("Раздел: " + name);
            System.out.println(content);
        });
    }
}
