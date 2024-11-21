package ru.app.drawing;

import java.util.ArrayList;
import java.util.List;

public class Drawing {
    private List<String> elements = new ArrayList<>();

    public void addElement(String element) {
        elements.add(element);
    }

    public void show() {
        System.out.println("Рисунок содержит: " + elements);
    }

    public DrawingMemento save() {
        return new DrawingMemento(new ArrayList<>(elements));
    }

    public void restore(DrawingMemento memento) {
        elements = memento.getElements();
    }
}
