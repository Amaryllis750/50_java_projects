package com.daniel.todolist.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.daniel.todolist.db_objs.*;
import com.daniel.todolist.gui.ToDoList;
import com.daniel.todolist.gui.todolist_components.MyButton;

public class LoginGUI extends JFrame{
    JTextField usernameField;
    JPasswordField passwordField;

    private Font textFieldFont = new Font("Dialog", Font.PLAIN, 20); 
    private Font labelFont = new Font("Dialog", Font.PLAIN, 14);

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

        // create the login or signup icon
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL is = classLoader.getResource("images\\profile.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ImageIcon loginImgIcon = new ImageIcon(img);
        JLabel imgLabel = new JLabel(loginImgIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(imgLabel, gbc);

        // create the username label
        gbc.gridy++;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(labelFont);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 15, 0, 0);
        background.add(usernameLabel, gbc);

        // username textfield
        gbc.gridy++;
        usernameField = new JTextField(20);
        usernameField.setFont(textFieldFont);
        gbc.insets = new Insets(2, 15, 15, 15);
        background.add(usernameField, gbc);

        // password label
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);
        gbc.insets = new Insets(0, 15, 0, 0);
        background.add(passwordLabel, gbc);

        // password textfield
        gbc.gridy++;
        passwordField = new JPasswordField(20);
        passwordField.setFont(textFieldFont);
        gbc.insets = new Insets(2, 15, 15, 15);
        background.add(passwordField, gbc);


        // this is the login button
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MyButton loginButton = new MyButton("Login");
        loginButton.setFont(textFieldFont);
        loginButton.addActionListener(event -> loginUser());
        background.add(loginButton, gbc);

        // this is the link that will lead to the registration page
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel signUpLabel = new JLabel("<html><a href=\"#\"> Don't have an account?</a></html>");
        signUpLabel.setFont(textFieldFont);
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
            new ToDoList(user, "My Todo-List");
            this.dispose();
        }
    }

    public void goToRegister(){
        this.dispose();
        new RegisterGUI("Create a New Account");
    }
}
