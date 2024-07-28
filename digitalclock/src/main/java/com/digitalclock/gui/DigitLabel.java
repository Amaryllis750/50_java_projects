package com.digitalclock.gui;

import javax.swing.*;
import java.awt.*;

public class DigitLabel extends JLabel{
    private Font textFont = new Font("Dubai", Font.BOLD, 60);

    public DigitLabel(String text){
        super(text);
        setOpaque(true);
        setBackground(Color.darkGray);
        setForeground(Color.white);
        setFont(textFont);
    }

    public DigitLabel(){
        this("00");
    }

}
