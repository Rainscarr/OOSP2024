package ru.app.ui;

import ru.app.services.TestService;

import javax.swing.*;
import java.awt.*;

public class CreateTestFrame extends JFrame {
    public CreateTestFrame() {
        setTitle("Create Test");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        add(panel);

        JLabel titleLabel = new JLabel("Test Title:");
        JTextField titleField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea();

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionArea);
        panel.add(saveButton);
        panel.add(cancelButton);

        saveButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();

            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Test title is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int testId = TestService.createTestAndGetId(title, description);
            if (testId > 0) {
                JOptionPane.showMessageDialog(this, "Test created successfully!");
                new AddQuestionFrame(testId);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create test.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}
