package ru.app.chats;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String name;
    private List<User> users = new ArrayList<>();

    public Chat(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void sendMessage(String message) {
        System.out.println(name + ": " + message);
        for (User user : users) {
            user.notify(name, message);
        }
    }
}
