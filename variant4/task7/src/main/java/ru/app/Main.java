package ru.app;

import ru.app.builders.SimpleDocumentBuilder;
import ru.app.document.Document;

public class Main {
    public static void main(String[] args) {
        SimpleDocumentBuilder builder = new SimpleDocumentBuilder();
        builder.addTitle("Заголовок документа")
                .addSection("Введение", "Это введение в документ")
                .addSection("Заключение", "Это заключение документа");

        Document document = builder.build();
        document.show();
    }
}
