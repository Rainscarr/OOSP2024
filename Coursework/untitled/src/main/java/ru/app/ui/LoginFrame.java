package ru.app.ui;

import ru.app.services.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        add(panel);

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());

            if (login.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Login and password are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            var user = UserService.login(login, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new MainFrame(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> new RegisterFrame());

        setVisible(true);
    }
}
