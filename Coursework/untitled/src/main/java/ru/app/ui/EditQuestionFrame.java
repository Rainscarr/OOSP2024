package ru.app.ui;

import ru.app.models.Question;
import ru.app.services.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditQuestionFrame extends JFrame {
    public EditQuestionFrame(Question question, DefaultListModel<Question> questionListModel) {
        setTitle("Edit Question");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        JLabel questionLabel = new JLabel("Question Text:");
        JTextArea questionArea = new JTextArea(question.getText());
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        JScrollPane questionScrollPane = new JScrollPane(questionArea);

        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(questionScrollPane, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        List<OptionComponent> optionComponents = new ArrayList<>();
        String[] options = question.getOptions().split(",");
        String[] correctAnswers = question.getCorrectAnswer().split(",");

        for (String option : options) {
            boolean isCorrect = false;
            for (String correct : correctAnswers) {
                if (option.trim().equals(correct.trim())) {
                    isCorrect = true;
                    break;
                }
            }
            addOptionField(optionsPanel, optionComponents, option, isCorrect);
        }

        JScrollPane optionsScrollPane = new JScrollPane(optionsPanel);
        panel.add(optionsScrollPane, BorderLayout.CENTER);

        JButton addOptionButton = new JButton("Add Option");
        addOptionButton.addActionListener(e -> addOptionField(optionsPanel, optionComponents, "", false));

        optionsPanel.add(addOptionButton);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save Changes");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            String updatedText = questionArea.getText().trim();
            if (updatedText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Question text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> updatedOptions = new ArrayList<>();
            List<String> updatedCorrectAnswers = new ArrayList<>();

            for (OptionComponent component : optionComponents) {
                String optionText = component.optionField.getText().trim();
                if (!optionText.isEmpty()) {
                    updatedOptions.add(optionText);
                    if (component.checkBox.isSelected()) {
                        updatedCorrectAnswers.add(optionText);
                    }
                }
            }

            if (updatedOptions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add at least one option.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updatedCorrectAnswers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one correct answer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            question.setText(updatedText);
            question.setOptions(String.join(",", updatedOptions));
            question.setCorrectAnswer(String.join(",", updatedCorrectAnswers));

            QuestionService.updateQuestion(question);
            questionListModel.setElementAt(question, questionListModel.indexOf(question));
            JOptionPane.showMessageDialog(this, "Question updated successfully!");
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        controlPanel.add(saveButton);
        controlPanel.add(cancelButton);
        panel.add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addOptionField(JPanel optionsPanel, List<OptionComponent> optionComponents, String text, boolean isCorrect) {
        JPanel optionPanel = new JPanel(new BorderLayout());
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(isCorrect);
        JTextField optionField = new JTextField(text, 30);
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(e -> {
            optionsPanel.remove(optionPanel);
            optionComponents.removeIf(option -> option.checkBox == checkBox && option.optionField == optionField);
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

    private static class OptionComponent {
        JCheckBox checkBox;
        JTextField optionField;

        public OptionComponent(JCheckBox checkBox, JTextField optionField) {
            this.checkBox = checkBox;
            this.optionField = optionField;
        }
    }
}
