package com.quizapp.gui.quizcomponents;

import javax.swing.*;
import java.awt.*;

public class OptionButton extends JButton{
    private boolean buttonIsSelected;
    private Font buttonFont = new Font("Dialog", Font.PLAIN, 18);
    private Color defaultForeColor = Color.black;
    private Color defaultBackColor = Color.white;
    private Color selectBackColor = new Color(103, 237, 84);
    private Color selectForeColor = Color.white;

    public OptionButton(String text){
        super(text);
        buttonIsSelected = false;
        setDefaultColor();
        setFont(buttonFont);
        setHorizontalAlignment(SwingConstants.LEFT);
        // setBorder(BorderFactory.createLineBorder(Color.lightGray));
        
    }

    public void setDefaultColor(){
        setColor(defaultBackColor, defaultForeColor);
    }

    public void setSelectedColor(){
        setColor(selectBackColor, selectForeColor);
    }

    private void setColor(Color bg, Color fg){
        setForeground(fg);
        setBackground(bg);
    }

    public void setButtonIsSelected(boolean b){
        this.buttonIsSelected = b;
        if(b == false){
            setDefaultColor();
        }
        else{
            setSelectedColor();
        }
    }

    public boolean getButtonIsSelected(){
        return this.buttonIsSelected;
    }
}
