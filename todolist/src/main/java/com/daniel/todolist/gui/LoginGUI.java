package com.daniel.todolist.gui;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame{
    public LoginGUI(String title){
        super(title);
        
        setSize(460, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    public void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new GridBagLayout());

        // set the constraint for the layout
        GridBagConstraints gbc = new GridBagConstraints();

        // create the username label and textfield
        

        getContentPane().add(BorderLayout.CENTER, background);
        pack();
    }
}
