package ru.app.models;

public class Result {
    private int id;
    private int userId;
    private int testId;
    private int score;
    private String date;

    public Result(int id, int userId, int testId, int score, String date) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.score = score;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTestId() {
        return testId;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
