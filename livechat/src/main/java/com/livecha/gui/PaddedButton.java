package com.livecha.gui;

import javax.swing.*;
import java.awt.*;

public class PaddedButton extends JButton{
    private Insets defaultInsets = new Insets(5, 50, 5, 50);

    public PaddedButton(){
        this("My Padded Button");
    }

    public PaddedButton(String text){
        super(text);
        setMargin(defaultInsets);
    }

    public PaddedButton(String text, Insets padding){
        this(text);
        setMargin(padding);
    }
}
