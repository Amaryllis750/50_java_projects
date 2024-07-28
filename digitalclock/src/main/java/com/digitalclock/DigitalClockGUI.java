package com.digitalclock;

import javax.swing.*;
import java.awt.*;

public class DigitalClockGUI extends JFrame{
    private Font titleFont = new Font("Dubai", Font.BOLD, 72);

    public DigitalClockGUI(){
        super("Digital Clock");
        setLocationRelativeTo(null);
        setupGUI();
    }

    private void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Digital Clock");

        TimePanel timePanel = new TimePanel();

        titleLabel.setFont(titleFont);
        background.add(titleLabel);
        background.add(timePanel);

        getContentPane().add(BorderLayout.CENTER, background);
        pack();
        setVisible(true);
        timePanel.start();
    }
}
