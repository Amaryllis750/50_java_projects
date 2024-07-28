package com.digitalclock;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

public class AppManager {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch(UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
        new DigitalClockGUI();
    }
}
