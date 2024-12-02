package ru.app.ui;

import ru.app.models.Question;
import ru.app.models.Test;
import ru.app.models.User;
import ru.app.services.QuestionService;
import ru.app.services.ResultService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestFrame extends JFrame {
    private final List<Question> questions;
    private final List<List<String>> studentAnswers;
    private int currentQuestionIndex = 0;
    private final User user;
    private final Test test;

    private JTextArea questionArea;
    private JPanel optionsPanel;

    public TestFrame(User user, Test test) {
        this.user = user;
        this.test = test;
        this.questions = QuestionService.getQuestionsForTest(test.getId());
        this.studentAnswers = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            studentAnswers.add(new ArrayList<>());
        }

        setTitle("Take Test: " + test.getTitle());
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        JScrollPane questionScrollPane = new JScrollPane(questionArea);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        JPanel questionListPanel = new JPanel();
        questionListPanel.setLayout(new BoxLayout(questionListPanel, BoxLayout.Y_AXIS));
        JScrollPane questionListScrollPane = new JScrollPane(questionListPanel);

        for (int i = 0; i < questions.size(); i++) {
            JButton questionButton = new JButton("Question " + (i + 1));
            final int questionIndex = i;
            questionButton.addActionListener(e -> showQuestion(questionIndex));
            questionListPanel.add(questionButton);
        }

        JPanel controlPanel = new JPanel();
        JButton finishButton = new JButton("Finish Test");
        finishButton.addActionListener(e -> finishTest());

        controlPanel.add(finishButton);

        add(questionScrollPane, BorderLayout.NORTH);
        add(new JScrollPane(optionsPanel), BorderLayout.CENTER);
        add(questionListScrollPane, BorderLayout.WEST);
        add(controlPanel, BorderLayout.SOUTH);

        if (!questions.isEmpty()) {
            showQuestion(0);
        } else {
            JOptionPane.showMessageDialog(this, "No questions available in this test.");
            dispose();
        }

        setVisible(true);
    }

    private void showQuestion(int questionIndex) {
        currentQuestionIndex = questionIndex;
        Question question = questions.get(questionIndex);
        questionArea.setText(question.getText());
        optionsPanel.removeAll();

        String[] options = question.getOptions().split(",");
        List<String> currentAnswers = studentAnswers.get(questionIndex);

        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);

            checkBox.setSelected(currentAnswers.contains(option));

            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    currentAnswers.add(option);
                } else {
                    currentAnswers.remove(option);
                }
            });

            optionsPanel.add(checkBox);
        }

        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private void finishTest() {
        int correctAnswersCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            List<String> correctAnswers = List.of(question.getCorrectAnswer().split(","));
            List<String> studentAnswer = studentAnswers.get(i);

            if (correctAnswers.containsAll(studentAnswer) && studentAnswer.containsAll(correctAnswers)) {
                correctAnswersCount++;
            }
        }

        int totalQuestions = questions.size();
        int score = (int) ((double) correctAnswersCount / totalQuestions * 100);

        if (ResultService.saveResult(user.getId(), test.getId(), score)) {
            JOptionPane.showMessageDialog(this, "Test completed! Your score: " + score + "%");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save your result.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }
}
