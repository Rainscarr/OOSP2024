package ru.app.chats;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void notify(String chatName, String message) {
        System.out.println(name + " получил сообщение из " + chatName + ": " + message);
    }
}
