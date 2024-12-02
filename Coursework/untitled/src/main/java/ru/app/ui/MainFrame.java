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
            JButton viewResultsButton = new JButton("View Results");
            JButton editTestButton = new JButton("Edit Test");
            JButton logoutButton = new JButton("Logout");

            createTestButton.addActionListener(e -> new CreateTestFrame());
            viewResultsButton.addActionListener(e -> new TeacherResultsFrame(user));
            editTestButton.addActionListener(e -> {
                List<Test> tests = TestService.getAllTests();
                if (tests.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No tests available for editing.");
                    return;
                }

                Test selectedTest = (Test) JOptionPane.showInputDialog(
                        this,
                        "Select a test to edit:",
                        "Edit Test",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tests.toArray(),
                        tests.get(0)
                );

                if (selectedTest != null) {
                    new EditTestFrame(selectedTest);
                }
            });

            logoutButton.addActionListener(e -> {
                dispose();
                new LoginFrame();
            });

            panel.add(createTestButton);
            panel.add(viewResultsButton);
            panel.add(editTestButton);
            panel.add(logoutButton);
        } else if (user.getRole().equalsIgnoreCase("student")) {
            JButton takeTestButton = new JButton("Take Test");
            JButton viewMyResultsButton = new JButton("View My Results");
            JButton logoutButton = new JButton("Logout");

            takeTestButton.addActionListener(e -> new TakeTestFrame(user));

            viewMyResultsButton.addActionListener(e -> new StudentResultsFrame(user));

            logoutButton.addActionListener(e -> {
                dispose();
                new LoginFrame();
            });

            panel.add(takeTestButton);
            panel.add(viewMyResultsButton);
            panel.add(logoutButton);
        }

        setVisible(true);
    }
}
