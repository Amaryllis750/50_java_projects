package com.daniel.todolist.gui;

import javax.swing.*;

import com.daniel.todolist.db_objs.MyJDBC;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.function.Function;

public class RegisterGUI extends JFrame {
    private Font guiFont = new Font("Dialog", Font.PLAIN, 20);
    private JTextField fullnameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterGUI(String title) {
        super(title);
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI() {
        JPanel background = new JPanel();
        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // setup name label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Fullname");
        nameLabel.setFont(guiFont);
        background.add(nameLabel, gbc);

        // this is the name field
        gbc.gridx = 1;
        gbc.gridy = 0;
        fullnameField = new JTextField(20);
        fullnameField.setFont(guiFont);
        background.add(fullnameField, gbc);

        // setup email label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(guiFont);
        background.add(emailLabel, gbc);

        // this is the email field
        gbc.gridx = 1;
        gbc.gridy = 1;
        emailField = new JTextField(20);
        emailField.setFont(guiFont);
        background.add(emailField, gbc);

        // setup username label
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(guiFont);
        background.add(usernameLabel, gbc);

        // this is the username field
        gbc.gridx = 1;
        gbc.gridy = 2;
        usernameField = new JTextField(20);
        usernameField.setFont(guiFont);
        background.add(usernameField, gbc);

        // setup password label
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(guiFont);
        background.add(passwordLabel, gbc);

        // this is the name field
        gbc.gridx = 1;
        gbc.gridy = 3;
        passwordField = new JPasswordField(20);
        passwordField.setFont(guiFont);
        background.add(passwordField, gbc);

        // register button
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(event -> registerUser());
        registerButton.setFont(guiFont);
        background.add(registerButton, gbc);

        // this is a link that will lead to the LoginGUI
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        JLabel signInLabel = new JLabel("<html><a href=\"#\"> Already have an account with us? </a></html>");
        signInLabel.setFont(guiFont);
        signInLabel.setForeground(new Color(118, 181, 197));
        signInLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                goToLogin();
            };
        });
        background.add(signInLabel, gbc);

        getContentPane().add(BorderLayout.CENTER, background);
        pack();
        setVisible(true);
    }

    public String parseInput(String fullname, String email, String username, String password) {
        // first strip all whitespaces from the input
        fullname = fullname.strip();
        email = email.strip();
        username = username.strip();
        password = password.strip();
        Function<String, Boolean> greaterThan6 = (text) -> text.length() > 6;

        if (fullname.isBlank() || email.isBlank() || username.isBlank() || password.isBlank()) {
            return "One of your fields is blank";
        } else if (!(greaterThan6.apply(password))) {
            return "Your password is not up to 7 characters";
        }
        return "";

        // check that the password is more than 6 strings
    }

    public static boolean moreThan6Strings(String text) {
        return text.length() >= 6;
    }

    public void registerUser() {
        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        String parseResult = parseInput(fullname, email, username, password);

        if (parseResult == "") {
            boolean registerdUser = MyJDBC.registerUsers(fullname, email, username, password);
            if (registerdUser == true) {
                // create a new login gui
                new LoginGUI("Login Into Your Account");
                // close this window
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Your username must already exist");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, parseResult);
        }
    }

    private void goToLogin() {
        new LoginGUI("Login Into Your Account");
        this.dispose();
    }
}
