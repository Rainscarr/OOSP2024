package ru.app.ui;

import ru.app.models.Question;
import ru.app.models.Test;
import ru.app.services.QuestionService;
import ru.app.services.TestService;
import ru.app.utils.SimpleDocumentListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditTestFrame extends JFrame {
    private boolean changesMade = false; // Флаг шоб отслеживать изменения

    public EditTestFrame(Test test) {
        setTitle("Edit Test: " + test.getTitle());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        JPanel headerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel titleLabel = new JLabel("Test Title:");
        JTextField titleField = new JTextField(test.getTitle());
        JLabel descriptionLabel = new JLabel("Test Description:");
        JTextField descriptionField = new JTextField(test.getDescription() == null ? "" : test.getDescription());

        titleField.getDocument().addDocumentListener((SimpleDocumentListener) e -> changesMade = true);
        descriptionField.getDocument().addDocumentListener((SimpleDocumentListener) e -> changesMade = true);

        headerPanel.add(titleLabel);
        headerPanel.add(titleField);
        headerPanel.add(descriptionLabel);
        headerPanel.add(descriptionField);

        panel.add(headerPanel, BorderLayout.NORTH);

        DefaultListModel<Question> questionListModel = new DefaultListModel<>();
        JList<Question> questionList = new JList<>(questionListModel);
        JScrollPane questionScrollPane = new JScrollPane(questionList);

        List<Question> questions = QuestionService.getQuestionsForTest(test.getId());
        for (Question question : questions) {
            questionListModel.addElement(question);
        }

        JPanel questionControlPanel = new JPanel(new FlowLayout());
        JButton addQuestionButton = new JButton("Add Question");
        JButton editQuestionButton = new JButton("Edit Question");
        JButton deleteQuestionButton = new JButton("Delete Question");

        addQuestionButton.addActionListener(e -> {
            new AddQuestionFrame(test.getId());
            changesMade = true;
        });

        editQuestionButton.addActionListener(e -> {
            Question selectedQuestion = questionList.getSelectedValue();
            if (selectedQuestion != null) {
                new EditQuestionFrame(selectedQuestion, questionListModel);
                changesMade = true;
            } else {
                JOptionPane.showMessageDialog(this, "Please select a question to edit.");
            }
        });

        deleteQuestionButton.addActionListener(e -> {
            Question selectedQuestion = questionList.getSelectedValue();
            if (selectedQuestion != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this question?");
                if (confirm == JOptionPane.YES_OPTION) {
                    if (QuestionService.deleteQuestion(selectedQuestion.getId())) {
                        questionListModel.removeElement(selectedQuestion);
                        changesMade = true;
                        JOptionPane.showMessageDialog(this, "Question deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete question.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a question to delete.");
            }
        });

        questionControlPanel.add(addQuestionButton);
        questionControlPanel.add(editQuestionButton);
        questionControlPanel.add(deleteQuestionButton);

        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.add(questionScrollPane, BorderLayout.CENTER);
        questionPanel.add(questionControlPanel, BorderLayout.SOUTH);

        panel.add(questionPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save Changes");
        JButton closeButton = new JButton("Close");

        saveButton.addActionListener(e -> {
            if (changesMade) {
                String newTitle = titleField.getText().trim();
                String newDescription = descriptionField.getText().trim();

                if (!newTitle.isEmpty()) {
                    test.setTitle(newTitle);
                    test.setDescription(newDescription.isEmpty() ? null : newDescription);
                    TestService.updateTest(test);
                    JOptionPane.showMessageDialog(this, "Changes saved successfully!");
                    changesMade = false; // Сбрасываем флаг изменений
                } else {
                    JOptionPane.showMessageDialog(this, "Test title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No changes were made.");
            }
        });

        closeButton.addActionListener(e -> dispose());

        controlPanel.add(saveButton);
        controlPanel.add(closeButton);
        panel.add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
