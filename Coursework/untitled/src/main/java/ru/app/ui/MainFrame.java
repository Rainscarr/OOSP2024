package ru.app.ui;

import ru.app.models.Test;
import ru.app.models.User;
import ru.app.services.TestService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    public MainFrame(User user) {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        add(panel);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getLogin() + " (" + user.getRole() + ")");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel);

        if (user.getRole().equalsIgnoreCase("teacher")) {
            JButton createTestButton = new JButton("Create Test");
            createTestButton.addActionListener(e -> new CreateTestFrame());

            JButton viewResultsButton = new JButton("View Results");
            viewResultsButton.addActionListener(e -> new TeacherResultsFrame(user));

            JButton editTestButton = new JButton("Edit Tests");
            editTestButton.addActionListener(e -> openEditTestSelector());

            panel.add(createTestButton);
            panel.add(viewResultsButton);
            panel.add(editTestButton);

        } else if (user.getRole().equalsIgnoreCase("student")) {
            JButton takeTestButton = new JButton("Take Test");
            takeTestButton.addActionListener(e -> new TakeTestFrame(user));

            JButton viewMyResultsButton = new JButton("View My Results");
            viewMyResultsButton.addActionListener(e -> new StudentResultsFrame(user));

            panel.add(takeTestButton);
            panel.add(viewMyResultsButton);
        }

        setVisible(true);
    }

    private void openEditTestSelector() {
        List<Test> tests = TestService.getAllTests(); // Получаем все тесты
        if (tests.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tests available for editing.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] testTitles = tests.stream().map(Test::getTitle).toArray(String[]::new);
        String selectedTestTitle = (String) JOptionPane.showInputDialog(
                this,
                "Select a test to edit:",
                "Edit Test",
                JOptionPane.PLAIN_MESSAGE,
                null,
                testTitles,
                testTitles[0]
        );

        if (selectedTestTitle != null) {
            Test selectedTest = tests.stream()
                    .filter(test -> test.getTitle().equals(selectedTestTitle))
                    .findFirst()
                    .orElse(null);

            if (selectedTest != null) {
                new EditTestFrame(selectedTest);
            }
        }
    }
}
