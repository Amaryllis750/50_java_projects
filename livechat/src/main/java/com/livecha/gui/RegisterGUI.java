package com.livecha.gui;

import java.util.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.livecha.db_objects.*;
import com.livecha.objects.*;

import java.awt.*;
import java.awt.image.*;


public class RegisterGUI extends JFrame{

    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private GridBagConstraints gbc = new GridBagConstraints();
    private Insets labelInsets = new Insets(0, 15, 0, 0);
    private Insets fieldInsets = new Insets(0, 15, 15, 15);
    private Insets buttonInsets = new Insets(15, 0, 15, 0);
    private JTextField usernameField, fullnameField;
    private JPasswordField passwordField, rePasswordField;
    private Font fieldFont = new Font("Droid sans", Font.PLAIN, 18);
    private Font labelFont = new Font("Century Gothic", Font.PLAIN, 14);
    private Font buttonFont = new Font("Century Gothic", Font.PLAIN, 18);
    private Color themeColor = new Color(148, 216, 45);
    // this is the color of the label that links back to the register frame
    private String colorStyle = "color:rgb(82, 121, 23)";
    public RegisterGUI(){
        super("Create An Account");
        setupGUI();
    }


    private void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        ImageIcon profileIcon = setupImage();
        JLabel profileIconLabel = new JLabel(profileIcon);
        background.add(profileIconLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelInsets;
        JLabel fullnameLabel = new JLabel("FullName");
        fullnameLabel.setFont(labelFont);
        background.add(fullnameLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = fieldInsets;
        fullnameField = new JTextField(20);
        fullnameField.setFont(fieldFont);
        background.add(fullnameField, gbc);


        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelInsets;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(labelFont);
        background.add(usernameLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = fieldInsets;
        usernameField = new JTextField(20);
        usernameField.setFont(fieldFont);
        background.add(usernameField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelInsets;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);
        background.add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = fieldInsets;
        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        background.add(passwordField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelInsets;
        JLabel rePasswordLabel = new JLabel("Re-Enter Password");
        rePasswordLabel.setFont(labelFont);
        background.add(rePasswordLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = fieldInsets;
        rePasswordField = new JPasswordField(20);
        rePasswordField.setFont(fieldFont);
        background.add(rePasswordField, gbc);

        gbc.gridy++;
        gbc.insets = buttonInsets;
        PaddedButton registerButton = new PaddedButton("Create Account");
        registerButton.setBackground(themeColor);
        registerButton.setForeground(Color.white);
        registerButton.setFont(buttonFont);
        registerButton.addActionListener(event -> registerUser());
        background.add(registerButton, gbc);

        gbc.gridy++;
        JLabel goToRegisterLabel = new JLabel(String.format("<html><a href='#' style='%s'>Already have an Account with us?</a></html>", colorStyle));
        goToRegisterLabel.setFont(labelFont);
        background.add(goToRegisterLabel, gbc);
        

        getContentPane().add(BorderLayout.CENTER, background);
        pack();
        setVisible(true);
    }

    private ImageIcon setupImage(){
        InputStream is = classLoader.getResourceAsStream("images\\profile pic.png");
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(is);
        }   
        catch(IOException e){
            e.printStackTrace();
        }
        ImageIcon imgIcon = new ImageIcon(bufferedImage);
        return imgIcon;
    }

    private void registerUser(){
        if(checkInputs().isBool()){
            String fullnameInput = fullnameField.getText();
            String usernameInput = usernameField.getText();
            String passwordInput = String.valueOf(passwordField.getPassword());

            boolean registered = MyJDBC.insertUsers(fullnameInput, usernameInput, passwordInput);
            if(registered){
                this.dispose();
                new LoginGUI();
            }
            else{
                JOptionPane.showMessageDialog(this, "Sorry there must have been an issue creating an account for you");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, checkInputs().getMessage());
        }
    }

    private Result checkInputs(){
        String fullname = fullnameField.getText();
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());;
        String rePassword = String.valueOf(passwordField.getPassword());
        Result result = new Result(false, "");

        if(fullname.isBlank() || username.isBlank() || password.isBlank() || rePassword.isBlank()){
            result.setBool(false);
            result.setMessage("Your fields are empty");
            return result;
        }

        if(password.length() < 6){
            result.setBool(false);
            result.setMessage("Your password must have a length of greater than 6 characters");
            return result;
        }

        if(password != rePassword){
            result.setBool(false);
            result.setMessage("Your Passwords must be equal to each other");
        }
        System.out.println("passed");
        result.setBool(true);
        return result;
    }
}
