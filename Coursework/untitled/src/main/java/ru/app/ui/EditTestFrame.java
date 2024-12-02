package ru.app.ui;

import ru.app.models.Question;
import ru.app.models.Test;
import ru.app.services.QuestionService;
import ru.app.services.TestService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditTestFrame extends JFrame {

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

        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.add(questionScrollPane, BorderLayout.CENTER);

        JButton addQuestionButton = new JButton("Add Question");
        JButton editQuestionButton = new JButton("Edit Question");
        JButton deleteQuestionButton = new JButton("Delete Question");

        addQuestionButton.addActionListener(e -> new AddQuestionFrame(test.getId()));

        editQuestionButton.addActionListener(e -> {
            Question selectedQuestion = questionList.getSelectedValue();
            if (selectedQuestion != null) {
                new EditQuestionFrame(selectedQuestion, questionListModel);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a question to edit.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        deleteQuestionButton.addActionListener(e -> {
            Question selectedQuestion = questionList.getSelectedValue();
            if (selectedQuestion != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this question?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (QuestionService.deleteQuestion(selectedQuestion.getId())) {
                        questionListModel.removeElement(selectedQuestion);
                        JOptionPane.showMessageDialog(this, "Question deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete question.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a question to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel questionButtonsPanel = new JPanel();
        questionButtonsPanel.add(addQuestionButton);
        questionButtonsPanel.add(editQuestionButton);
        questionButtonsPanel.add(deleteQuestionButton);

        questionPanel.add(questionButtonsPanel, BorderLayout.SOUTH);
        panel.add(questionPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save Changes");
        JButton deleteTestButton = new JButton("Delete Test");
        JButton closeButton = new JButton("Close");

        saveButton.addActionListener(e -> {
            String newTitle = titleField.getText().trim();
            String newDescription = descriptionField.getText().trim();

            if (!newTitle.isEmpty()) {
                test.setTitle(newTitle);
                test.setDescription(newDescription.isEmpty() ? null : newDescription);
                if (TestService.updateTest(test)) {
                    JOptionPane.showMessageDialog(this, "Changes saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save changes.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Test title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteTestButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this test?\nThis will also delete all associated questions.",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                QuestionService.deleteQuestionsByTestId(test.getId());
                if (TestService.deleteTest(test.getId())) {
                    JOptionPane.showMessageDialog(this, "Test deleted successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete test.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        closeButton.addActionListener(e -> dispose());

        controlPanel.add(saveButton);
        controlPanel.add(deleteTestButton);
        controlPanel.add(closeButton);
        panel.add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
