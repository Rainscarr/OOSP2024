package ru.app.ui;

import ru.app.services.UserService;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        add(panel);

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email (optional):");
        JTextField emailField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");

        JRadioButton studentButton = new JRadioButton("Student");
        JRadioButton teacherButton = new JRadioButton("Teacher");
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(studentButton);
        roleGroup.add(teacherButton);

        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(roleLabel);
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rolePanel.add(studentButton);
        rolePanel.add(teacherButton);
        panel.add(rolePanel);
        panel.add(registerButton);
        panel.add(cancelButton);

        registerButton.addActionListener(e -> {
            String login = loginField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String email = emailField.getText().trim();
            String role = studentButton.isSelected() ? "student" : teacherButton.isSelected() ? "teacher" : null;

            if (login.isEmpty() || password.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(this, "Login, password, and role are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (UserService.register(login, password, email, role)) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login already exists. Please try another one.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}
