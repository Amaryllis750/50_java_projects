package com.texteditor.gui;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame{
    public TextEditor(){
        super("New File");
        setupGUI();
    }

    private void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));


        getContentPane().add(BorderLayout.CENTER, background);
        setSize(600, 600);
        setVisible(true);
    }
}
