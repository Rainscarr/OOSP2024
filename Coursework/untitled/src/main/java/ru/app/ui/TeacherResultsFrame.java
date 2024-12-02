package ru.app.ui;

import ru.app.models.Result;
import ru.app.models.Test;
import ru.app.models.User;
import ru.app.services.ResultService;
import ru.app.services.TestService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TeacherResultsFrame extends JFrame {
    public TeacherResultsFrame(User user) {
        setTitle("Teacher Results Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        JLabel titleLabel = new JLabel("Test Results", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        String[] testColumnNames = {"Test ID", "Test Name", "View Results"};
        DefaultTableModel testTableModel = new DefaultTableModel(testColumnNames, 0);
        JTable testTable = new JTable(testTableModel);
        JScrollPane testScrollPane = new JScrollPane(testTable);

        List<Test> tests = TestService.getAllTests();
        for (Test test : tests) {
            Object[] row = {
                    test.getId(),
                    test.getTitle(),
                    "View Results"
            };
            testTableModel.addRow(row);
        }

        String[] resultColumnNames = {"Student ID", "Student Name", "Score (%)", "Date"};
        DefaultTableModel resultTableModel = new DefaultTableModel(resultColumnNames, 0);
        JTable resultTable = new JTable(resultTableModel);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, testScrollPane, resultScrollPane);
        splitPane.setDividerLocation(300);
        panel.add(splitPane, BorderLayout.CENTER);

        testTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && testTable.getSelectedRow() != -1) {
                int selectedRow = testTable.getSelectedRow();
                int testId = (int) testTableModel.getValueAt(selectedRow, 0);

                resultTableModel.setRowCount(0);
                List<Result> results = ResultService.getResultsForTest(testId);
                for (Result result : results) {
                    String studentName = "Student " + result.getUserId();
                    Object[] resultRow = {
                            result.getUserId(),
                            studentName,
                            result.getScore(),
                            result.getDate()
                    };
                    resultTableModel.addRow(resultRow);
                }
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
