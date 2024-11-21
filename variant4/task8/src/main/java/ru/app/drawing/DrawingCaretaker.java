package ru.app.drawing;

import java.util.Stack;

public class DrawingCaretaker {
    private Stack<DrawingMemento> history = new Stack<>();

    public void save(DrawingMemento memento) {
        history.push(memento);
    }

    public DrawingMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}
