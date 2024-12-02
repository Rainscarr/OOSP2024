package ru.app.services;

import ru.app.db.Database;
import ru.app.models.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultService {
    public static boolean saveResult(int userId, int testId, int score) {
        // Используем московское время (UTC+3)
        String sql = "INSERT INTO Results (user_id, test_id, score, date) VALUES (?, ?, ?, datetime('now', 'localtime'))";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, testId);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Получает список всех результатов студента.
     *
     * @param userId ID пользователя (студента).
     * @return Список результатов.
     */
    public static List<Result> getResultsForUser(int userId) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM Results WHERE user_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(new Result(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("test_id"),
                        rs.getInt("score"),
                        rs.getString("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public static List<Result> getResultsForTest(int testId) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM Results WHERE test_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(new Result(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("test_id"),
                        rs.getInt("score"),
                        rs.getString("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
