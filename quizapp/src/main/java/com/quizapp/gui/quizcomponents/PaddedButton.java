package com.quizapp.gui.quizcomponents;

import javax.swing.*;
import java.awt.*;

public class PaddedButton extends JButton{
    private Font buttonFont = new Font("Dubai", Font.PLAIN, 16);
    private Color backGroundColor = new Color(210, 97, 207);
    private Color foreGroundColor = Color.white;

    public PaddedButton(String text){
        super(text);
        setMargin(new Insets(10, 20, 10, 10));
        setFont(buttonFont);
        setBackground(backGroundColor);
        setForeground(foreGroundColor);
    }

    public PaddedButton(String text, Insets inset){
        this(text);
        setMargin(inset);
    }
}
