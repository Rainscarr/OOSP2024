package ru.app.services;

import ru.app.db.Database;
import ru.app.models.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public static List<Question> getQuestionsForTest(int testId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM Questions WHERE test_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                questions.add(new Question(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("options"),
                        rs.getString("correct_answer"),
                        rs.getInt("test_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static boolean addQuestion(String text, String options, String correctAnswers, int testId) {
        String sql = "INSERT INTO Questions (text, options, correct_answer, test_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, text);
            pstmt.setString(2, options);
            pstmt.setString(3, correctAnswers);
            pstmt.setInt(4, testId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateQuestion(Question question) {
        String sql = "UPDATE Questions SET text = ?, options = ?, correct_answer = ? WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getText());
            pstmt.setString(2, question.getOptions());
            pstmt.setString(3, question.getCorrectAnswer());
            pstmt.setInt(4, question.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteQuestionsByTestId(int testId) {
        String sql = "DELETE FROM Questions WHERE test_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, testId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteQuestion(int questionId) {
        String sql = "DELETE FROM Questions WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, questionId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
