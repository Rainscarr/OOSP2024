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

        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel instructionLabel = new JLabel("Select a test to start:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(instructionLabel, BorderLayout.NORTH);

        List<Test> tests = TestService.getAllTests();
        if (tests.isEmpty()) {
            JLabel noTestsLabel = new JLabel("No tests available.");
            noTestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(noTestsLabel, BorderLayout.CENTER);
        } else {
            DefaultListModel<Test> testListModel = new DefaultListModel<>();
            for (Test test : tests) {
                testListModel.addElement(test);
            }

            JList<Test> testList = new JList<>(testListModel);
            testList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(testList);

            JButton startButton = new JButton("Start Test");
            startButton.addActionListener(e -> {
                Test selectedTest = testList.getSelectedValue();
                if (selectedTest != null) {
                    new TestFrame(user, selectedTest);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a test to start.");
                }
            });

            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(startButton, BorderLayout.SOUTH);
        }

        add(mainPanel);
        setVisible(true);
    }
}
