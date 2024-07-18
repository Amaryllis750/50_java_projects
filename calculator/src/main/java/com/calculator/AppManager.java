package com.calculator;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.calculator.gui.CalculatorGUI;

import com.formdev.flatlaf.*;

public class AppManager {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch(UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
        new CalculatorGUI();
    }
}
