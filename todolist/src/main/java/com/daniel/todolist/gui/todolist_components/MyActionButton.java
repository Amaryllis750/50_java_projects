package com.daniel.todolist.gui.todolist_components;

import javax.swing.*;
import java.awt.*;

public class MyActionButton extends JButton{
    
    public MyActionButton(){
        this("My Button");
    }

    public MyActionButton(String text){
        super(text);
        setEnabled(false);
    }

    public MyActionButton(String text, Color color){
        this(text);
        setBackground(color);
    }

    public MyActionButton(Icon icon){
        super(icon);
        setBackground(Color.white);
        setEnabled(false);
        setBorder(null);
    }
}
