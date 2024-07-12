package com.daniel.todolist.gui;

import javax.swing.*;

import com.daniel.todolist.db_objs.MyJDBC;
import com.daniel.todolist.gui.todolist_components.MyButton;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Function;

public class RegisterGUI extends JFrame {
    private Font textFieldFont = new Font("Dialog", Font.PLAIN, 20);
    private Font labelFont = new Font("Dialog", Font.PLAIN, 14);
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

        // setup the register icon
        gbc.gridx = 0;
        gbc.gridy = 0;
        ImageIcon registerImgIcon = new ImageIcon("todolist\\src\\resources\\images\\profile.png");
        JLabel imgLabel = new JLabel(registerImgIcon);
        background.add(imgLabel, gbc);

        // setup name label
        gbc.gridy++;
        JLabel nameLabel = new JLabel("Fullname");
        nameLabel.setFont(labelFont);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 15, 0, 0);
        background.add(nameLabel, gbc);

        // this is the name field
        gbc.gridy++;
        fullnameField = new JTextField(20);
        fullnameField.setFont(textFieldFont);
        gbc.insets = new Insets(0, 15, 15, 15);
        background.add(fullnameField, gbc);

        // setup email label
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        gbc.insets = new Insets(0, 15, 0, 0);
        background.add(emailLabel, gbc);

        // this is the email field
        gbc.gridy++;
        emailField = new JTextField(20);
        emailField.setFont(textFieldFont);
        gbc.insets = new Insets(0, 15, 15, 15);
        background.add(emailField, gbc);

        // setup username label
        gbc.gridy++;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(labelFont);
        gbc.insets = new Insets(0, 15, 0, 0);
        background.add(usernameLabel, gbc);

        // this is the username field
        gbc.gridy++;
        usernameField = new JTextField(20);
        usernameField.setFont(textFieldFont);
        gbc.insets = new Insets(0, 15, 15, 15);
        background.add(usernameField, gbc);

        // setup password label
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);
        gbc.insets = new Insets(0, 15, 0, 0);
        background.add(passwordLabel, gbc);

        // this is the name field
        gbc.gridy++;
        passwordField = new JPasswordField(20);
        passwordField.setFont(textFieldFont);
        gbc.insets = new Insets(0, 15, 15, 15);
        background.add(passwordField, gbc);

        // register button
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MyButton registerButton = new MyButton("Register");
        registerButton.addActionListener(event -> registerUser());
        registerButton.setFont(textFieldFont);
        // set the grid bag constraints for the button
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 15, 15, 15);
        background.add(registerButton, gbc);

        // this is a link that will lead to the LoginGUI
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel signInLabel = new JLabel("<html><a href=\"#\"> Already have an account with us? </a></html>");
        signInLabel.setFont(textFieldFont);
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
