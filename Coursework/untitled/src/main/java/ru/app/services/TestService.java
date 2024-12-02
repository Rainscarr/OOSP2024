package ru.app.services;

import ru.app.db.Database;
import ru.app.models.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestService {

    public static int createTestAndGetId(String title, String description) {
        String sql = "INSERT INTO Tests (title, description) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Возвращаем ID созданного теста
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Ошибка при создании
    }


    public static List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM Tests";
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tests.add(new Test(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

   public static String getTestNameById(int testId) {
        String sql = "SELECT title FROM Tests WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown Test";
    }

    public static boolean updateTest(Test test) {
        String sql = "UPDATE Tests SET title = ?, description = ? WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, test.getTitle());
            pstmt.setString(2, test.getDescription());
            pstmt.setInt(3, test.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
