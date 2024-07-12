package com.daniel.todolist.gui.todolist_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideBarButton extends JButton {
    public SideBarButton(String title){
        super(title);
        setFont(new Font("Dialog",Font.BOLD, 16));
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 60));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(Color.darkGray);
                setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(Color.white);
                setForeground(Color.black);
            }
        });
    }
}
