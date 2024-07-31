package com.livecha;

import javax.swing.*;
import java.awt.*;

import com.formdev.flatlaf.FlatLightLaf;
import com.livecha.gui.*;

public class AppManager {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            setComponentsBackground(Color.white);
            new LoginGUI();

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private static void setComponentsBackground(Color color) {
        UIManager.put("Panel.background", color);
        UIManager.put("Button.background", color);
        UIManager.put("Label.background", color);
        UIManager.put("TextField.background", color);
        UIManager.put("TextArea.background", color);
        UIManager.put("ComboBox.background", color);
        UIManager.put("List.background", color);
        UIManager.put("Table.background", color);
        UIManager.put("ScrollPane.background", color);
    }
}
