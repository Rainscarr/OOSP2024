package ru.app.drawing;

import java.util.List;

public class DrawingMemento {
    private final List<String> elements;

    public DrawingMemento(List<String> elements) {
        this.elements = elements;
    }

    public List<String> getElements() {
        return elements;
    }
}
