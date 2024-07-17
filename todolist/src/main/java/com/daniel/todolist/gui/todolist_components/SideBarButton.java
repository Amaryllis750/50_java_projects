package com.daniel.todolist.gui.todolist_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideBarButton extends JButton {
    private boolean active = false;

    public SideBarButton(String title){
        super(title);
        setFont(new Font("Dialog",Font.BOLD, 16));
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 60));
        if(this.active == false){
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    setColor(Color.darkGray, Color.white);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    setColor(Color.white, Color.black);
                }
            });
        setActive(active);
        }
    }

    public boolean isActive(){
        return this.active;
    }

    public void setActive(boolean active){
        this.active = active;
        if(active == false){
            this.setColor(Color.white, Color.black);
        }
        else{
            this.setColor(Color.darkGray, Color.white);
        }
    }

    public void setColor(Color bg, Color fg){
        setBackground(bg);
        setForeground(fg);
    }
}
