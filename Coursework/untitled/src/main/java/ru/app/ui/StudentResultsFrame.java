package ru.app.ui;

import ru.app.models.Result;
import ru.app.models.User;
import ru.app.services.ResultService;
import ru.app.services.TestService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentResultsFrame extends JFrame {
    public StudentResultsFrame(User user) {
        setTitle("My Results");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        JLabel titleLabel = new JLabel("Your Test Results", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Test Name", "Score (%)", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultsTable = new JTable(tableModel);
        resultsTable.setEnabled(false);

        List<Result> results = ResultService.getResultsForUser(user.getId());
        for (Result result : results) {
            String testName = TestService.getTestNameById(result.getTestId());
            Object[] row = {testName, result.getScore(), result.getDate()};
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
