package com.quizapp.gui;

import javax.swing.*;
import java.awt.*;

public class QuizApp extends JFrame{
    public QuizApp(){
        super("Quiz App");
        setupGUI();
    }

    public void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Quiz App");
        background.add(BorderLayout.NORTH, titleLabel);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadQuizMenuItem = new JMenuItem("Load Quiz");
        JMenuItem createNewQuizItem = new JMenuItem("Create Quiz");
        fileMenu.add(loadQuizMenuItem);
        fileMenu.add(createNewQuizItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        getContentPane().add(BorderLayout.CENTER, background);
        setSize(400, 500);
        setVisible(true);
    }
}
