package com.livecha.gui;

import java.awt.image.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.livecha.db_objects.*;
import com.livecha.objects.*;

import java.io.*;
import java.util.Map;

public class LoginGUI extends JFrame{
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private GridBagConstraints gbc = new GridBagConstraints();
    private Insets labelInsets = new Insets(0, 15, 0, 0);
    private Insets fieldInsets = new Insets(0, 15, 15, 15);
    private Insets buttonInsets = new Insets(15, 0, 15, 0);
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Font fieldFont = new Font("Droid sans", Font.PLAIN, 18);
    private Font labelFont = new Font("Century Gothic", Font.PLAIN, 14);
    private Font buttonFont = new Font("Century Gothic", Font.PLAIN, 18);
    private Color themeColor = new Color(148, 216, 45);
    // this is the color of the label that links back to the register frame
    private String colorStyle = "color:rgb(82, 121, 23)";
    

    public LoginGUI(){
        super("Login");
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
        gbc.insets = buttonInsets;
        PaddedButton loginButton = new PaddedButton("Login");
        loginButton.setBackground(themeColor);
        loginButton.setForeground(Color.white);
        loginButton.setFont(buttonFont);
        loginButton.addActionListener(event -> login());
        background.add(loginButton, gbc);

        gbc.gridy++;
        JLabel goToRegisterLabel = new JLabel(String.format("<html><a href='#' style='%s'>Don't have an Account with us?</a></html>", colorStyle));
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

    private void login(){
        if(checkInputs().isBool()){
            String usernameInput = usernameField.getText();
            String passwordInput = String.valueOf(passwordField.getPassword());
            User user = MyJDBC.loginUser(usernameInput, passwordInput);
            if(user == null){
                JOptionPane.showMessageDialog(this, "Your username or password is incorrect");
            }
            else{
                JOptionPane.showMessageDialog(this, checkInputs().getMessage());
                new LiveChat(user);
                this.dispose();
            }
        }
        else{
            // this shows the message that was contained in the result
            JOptionPane.showMessageDialog(this, checkInputs().getMessage());
        }
    }

    private Result checkInputs(){
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        Result result = new Result(false, "");

        if(username.isBlank() || password.isBlank()){
            result.setBool(false);
            result.setMessage("Your fields should not be blank");
            return result;
        }

        if(password.length() < 6){
            result.setBool(false);
            result.setMessage("Your password should be more than 6 characters");
            return result;
        }
        
        result.setBool(true);
        result.setMessage("You have successfully logged in");
        return result;
    }
}
