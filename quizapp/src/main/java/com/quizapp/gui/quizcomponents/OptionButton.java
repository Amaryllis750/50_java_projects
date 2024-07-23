package com.quizapp.gui.quizcomponents;

import javax.swing.*;
import java.awt.*;

public class OptionButton extends JButton{
    private boolean buttonIsSelected;
    private Font buttonFont = new Font("Dialog", Font.PLAIN, 18);
    private Color defaultForeColor = Color.white;
    private Color defaultBackColor = Color.lightGray;
    private Color selectBackColor = new Color(103, 237, 84);
    private Color selectForeColor = Color.white;
    private int noOfTimesClicked = 0;

    public OptionButton(String text){
        super(text);
        buttonIsSelected = false;
        setDefaultColor();
        setFont(buttonFont);
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

    public void incrementNumberOfTimesClicked(){
        this.noOfTimesClicked++;
    }

    public int getNumberOfTimesClicked(){
        return this.noOfTimesClicked;
    }
}
