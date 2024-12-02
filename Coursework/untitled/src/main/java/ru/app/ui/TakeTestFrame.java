package ru.app.ui;

import ru.app.models.Test;
import ru.app.models.User;
import ru.app.services.TestService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TakeTestFrame extends JFrame {
    public TakeTestFrame(User user) {
        setTitle("Select a Test");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        add(panel);

        List<Test> tests = TestService.getAllTests();
        if (tests.isEmpty()) {
            JLabel noTestsLabel = new JLabel("No tests available.");
            noTestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(noTestsLabel);
        } else {
            JLabel instructionLabel = new JLabel("Select a test to start:");
            instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(instructionLabel);

            for (Test test : tests) {
                JButton testButton = new JButton(test.getTitle());
                testButton.addActionListener(e -> {
                    new TestFrame(user, test);
                    dispose();
                });
                panel.add(testButton);
            }
        }

        setVisible(true);
    }
}
