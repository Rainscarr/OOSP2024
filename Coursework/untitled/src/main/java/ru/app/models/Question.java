package ru.app.models;

public class Question {
    private int id;
    private String text;
    private String options;
    private String correctAnswer;
    private int testId;

    public Question(int id, String text, String options, String correctAnswer, int testId) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.testId = testId;
    }

    @Override
    public String toString() {
        return text;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getTestId() {
        return testId;
    }
}
