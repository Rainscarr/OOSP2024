package ru.app;

import ru.app.drawing.Drawing;
import ru.app.drawing.DrawingCaretaker;

public class Main {
    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        DrawingCaretaker caretaker = new DrawingCaretaker();

        drawing.addElement("Круг");
        caretaker.save(drawing.save());
        drawing.show();

        drawing.addElement("Квадрат");
        caretaker.save(drawing.save());
        drawing.show();

        drawing.addElement("Треугольник");
        drawing.show();

        System.out.println("Восстанавливаем состояние:");
        drawing.restore(caretaker.undo());
        drawing.show();

        drawing.restore(caretaker.undo());
        drawing.show();
    }
}
