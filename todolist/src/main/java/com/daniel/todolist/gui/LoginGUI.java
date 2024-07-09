package com.daniel.todolist.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.daniel.todolist.db_objs.*;
import com.daniel.todolist.gui.ToDoList;

public class LoginGUI extends JFrame{
    JTextField usernameField;
    JPasswordField passwordField;

    private Font guiFont = new Font("Dialog", Font.PLAIN, 20); 

    public LoginGUI(String title){
        super(title);
        
        setSize(460, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupGUI();
    } 

    public void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new GridBagLayout());

        // set the constraint for the layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // create the username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(guiFont);
        background.add(usernameLabel, gbc);

        // username textfield
        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField = new JTextField(20);
        usernameField.setFont(guiFont);
        background.add(usernameField, gbc);

        // password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(guiFont);
        background.add(passwordLabel, gbc);

        // password textfield
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        passwordField.setFont(guiFont);
        background.add(passwordField, gbc);


        // this is the login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(guiFont);
        loginButton.addActionListener(event -> loginUser());
        background.add(loginButton, gbc);

        // this is the link that will lead to the registration page
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel signUpLabel = new JLabel("<html><a href=\"#\"> Don't have an account?</a></html>");
        signUpLabel.setFont(guiFont);
        signUpLabel.setForeground(new Color(118,181,197));
        signUpLabel.addMouseListener(new MouseAdapter(){

            public void mouseClicked(MouseEvent e){
                goToRegister();
            }
        });
        background.add(signUpLabel, gbc);



        getContentPane().add(BorderLayout.CENTER, background);
        pack();
        setVisible(true);
    }

    public void loginUser(){
        String username = usernameField.getText();
        char[] passwordArray = passwordField.getPassword();
        String password = String.valueOf(passwordArray);

        User user = MyJDBC.loginUsers(username, password);
        if(user == null){
            String errorMessage = "Your username or password is incorrect";
            JOptionPane.showMessageDialog(this, errorMessage);
        }
        else{
            JOptionPane.showMessageDialog(this, "Welcome");
            new ToDoList(user);
            this.dispose();
        }
    }

    public void goToRegister(){
        this.dispose();
        new RegisterGUI("Create a New Account");
    }
}
