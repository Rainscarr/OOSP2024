package ru.app.builders;

import ru.app.document.Document;

public interface DocumentBuilder {
    DocumentBuilder addTitle(String title);

    DocumentBuilder addSection(String name, String content);

    Document build();
}
