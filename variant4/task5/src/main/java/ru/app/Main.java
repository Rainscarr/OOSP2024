package ru.app;

import ru.app.chats.Chat;
import ru.app.chats.User;

public class Main {
    public static void main(String[] args) {
        Chat chat1 = new Chat("Общий чат");
        Chat chat2 = new Chat("Рабочий чат");

        User user1 = new User("Иван");
        User user2 = new User("Мария");
        User user3 = new User("Анна");

        chat1.addUser(user1);
        chat1.addUser(user2);

        chat2.addUser(user2);
        chat2.addUser(user3);

        chat1.sendMessage("Привет всем");
        chat2.sendMessage("Начинаем совещание");
    }
}
