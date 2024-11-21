package ru.app.builders;

import ru.app.document.Document;

public class SimpleDocumentBuilder implements DocumentBuilder {
    private Document document = new Document();

    @Override
    public DocumentBuilder addTitle(String title) {
        document.setTitle(title);
        return this;
    }

    @Override
    public DocumentBuilder addSection(String name, String content) {
        document.addSection(name, content);
        return this;
    }

    @Override
    public Document build() {
        return document;
    }
}
