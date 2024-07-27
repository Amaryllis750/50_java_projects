package com.quizapp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.*;

import com.quizapp.gui.*;

import com.formdev.flatlaf.*;

public class AppManager {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Panel.background", Color.WHITE);
            UIManager.put("Button.background", Color.WHITE);
            UIManager.put("Label.background", Color.WHITE);
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextArea.background", Color.WHITE);
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("List.background", Color.WHITE);
            UIManager.put("Table.background", Color.WHITE);
            UIManager.put("ScrollPane.background", Color.WHITE);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new QuizApp();
    }
}
