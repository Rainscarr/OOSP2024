package ru.app.db;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:coursework.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL)) {
            Statement stmt = conn.createStatement();

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    login TEXT UNIQUE,
                    password TEXT,
                    email TEXT,
                    role TEXT
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Tests (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    description TEXT
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Questions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    text TEXT,
                    options TEXT,
                    correct_answer TEXT,
                    test_id INTEGER,
                    FOREIGN KEY (test_id) REFERENCES Tests (id)
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Results (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER,
                    test_id INTEGER,
                    score INTEGER,
                    date TEXT,
                    FOREIGN KEY (user_id) REFERENCES Users (id),
                    FOREIGN KEY (test_id) REFERENCES Tests (id)
                );
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
