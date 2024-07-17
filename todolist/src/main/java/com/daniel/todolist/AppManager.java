package com.daniel.todolist;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import jiconfont.swing.IconFontSwing;

import com.daniel.todolist.gui.*;
import com.daniel.todolist.gui.icons.FontAwesome;

public class AppManager {
    
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch(UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
        // register the font awesome font
        IconFontSwing.register(FontAwesome.getIconFont());
        new LoginGUI("Login Into Your Account");
    }
}
