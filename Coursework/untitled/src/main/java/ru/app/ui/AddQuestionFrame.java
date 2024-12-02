package ru.app.ui;

import ru.app.services.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionFrame extends JFrame {
    private final int testId;
    private final JTextArea questionField;
    private final JPanel optionsPanel;
    private final List<OptionComponent> optionComponents;

    public AddQuestionFrame(int testId) {
        this.testId = testId;
        this.optionComponents = new ArrayList<>();

        setTitle("Add Questions to Test");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel(new BorderLayout());
        JLabel questionLabel = new JLabel("Question:");
        questionField = new JTextArea(3, 50);
        questionField.setLineWrap(true);
        questionField.setWrapStyleWord(true);
        JScrollPane questionScrollPane = new JScrollPane(questionField);
        questionPanel.add(questionLabel, BorderLayout.NORTH);
        questionPanel.add(questionScrollPane, BorderLayout.CENTER);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        JScrollPane optionsScrollPane = new JScrollPane(optionsPanel);

        JButton addOptionButton = new JButton("Add Option");
        addOptionButton.addActionListener(e -> addOptionField());

        optionsPanel.add(addOptionButton);
        optionsPanel.revalidate();
        optionsPanel.repaint();

        JPanel controlPanel = new JPanel();
        JButton addQuestionButton = new JButton("Add Question");
        JButton finishTestButton = new JButton("Finish Test");

        addQuestionButton.addActionListener(e -> saveQuestion(addOptionButton));
        finishTestButton.addActionListener(e -> finishTest());

        controlPanel.add(addQuestionButton);
        controlPanel.add(finishTestButton);

        add(questionPanel, BorderLayout.NORTH);
        add(new JScrollPane(optionsPanel), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addOptionField() {
        JPanel optionPanel = new JPanel(new BorderLayout());

        JCheckBox checkBox = new JCheckBox();
        JTextField optionField = new JTextField(30);
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(e -> {
            optionsPanel.remove(optionPanel);
            optionComponents.removeIf(option -> option.checkBox == checkBox && option.optionField == optionField);

            if (optionComponents.isEmpty()) {
                addOptionField();
            }

            optionsPanel.revalidate();
            optionsPanel.repaint();
        });

        optionPanel.add(checkBox, BorderLayout.WEST);
        optionPanel.add(optionField, BorderLayout.CENTER);
        optionPanel.add(removeButton, BorderLayout.EAST);

        optionsPanel.add(optionPanel, optionsPanel.getComponentCount() - 1);
        optionsPanel.revalidate();
        optionsPanel.repaint();

        optionComponents.add(new OptionComponent(checkBox, optionField));
    }

    private void saveQuestion(JButton addOptionButton) {
        String questionText = questionField.getText().trim();
        if (questionText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Question text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> options = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();

        for (OptionComponent optionComponent : optionComponents) {
            String optionText = optionComponent.optionField.getText().trim();
            if (!optionText.isEmpty()) {
                options.add(optionText);
                if (optionComponent.checkBox.isSelected()) {
                    correctAnswers.add(optionText);
                }
            }
        }

        if (options.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one option.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (correctAnswers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one correct answer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String optionsString = String.join(",", options);
        String correctAnswersString = String.join(",", correctAnswers);

        if (QuestionService.addQuestion(questionText, optionsString, correctAnswersString, testId)) {
            JOptionPane.showMessageDialog(this, "Question added successfully!");

            questionField.setText("");
            optionComponents.clear();
            optionsPanel.removeAll();

            optionsPanel.add(addOptionButton);
            optionsPanel.revalidate();
            optionsPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save question.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finishTest() {
        JOptionPane.showMessageDialog(this, "Test creation completed!");
        dispose();
    }

    private static class OptionComponent {
        JCheckBox checkBox;
        JTextField optionField;

        public OptionComponent(JCheckBox checkBox, JTextField optionField) {
            this.checkBox = checkBox;
            this.optionField = optionField;
        }
    }
}
