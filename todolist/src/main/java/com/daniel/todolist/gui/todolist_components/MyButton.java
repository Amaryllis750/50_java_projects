package com.daniel.todolist.gui.todolist_components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyButton extends JButton{
    public MyButton(){
        this("My Button");
    }
    public MyButton(String title){
        super(title);
        setBackground(Color.darkGray);
        setForeground(Color.white);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                setBackground(new Color(0, 132, 232));
            }
            public void mouseExited(MouseEvent e){
                setBackground(Color.darkGray);
            }
        });
    }
}
