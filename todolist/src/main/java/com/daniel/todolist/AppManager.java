package com.daniel.todolist;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.*;
import com.formdev.flatlaf.FlatLightLaf;

import com.daniel.todolist.gui.*;

public class AppManager {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch(UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }

        new LoginGUI("Login Into Your Account");
    }
}
