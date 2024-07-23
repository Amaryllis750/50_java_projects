package com.quizapp.gui;

import java.io.*;

import javax.swing.*;
import java.awt.*;

import com.quizapp.gui.createquiz.*;
import com.quizapp.gui.quizcomponents.*;
import com.quizapp.entities.*;

public class QuizApp extends JFrame{
    private JPanel quizPanel;
    private Quiz quiz;
    private JPanel background;
    public QuizApp(){
        super("Quiz App");
        setupGUI();
    }

    public void setupGUI(){
        background = new JPanel();
        background.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Quiz App");
        background.add(BorderLayout.NORTH, titleLabel);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadQuizMenuItem = new JMenuItem("Load Quiz");
        loadQuizMenuItem.addActionListener(event -> loadQuiz());
        JMenuItem createNewQuizItem = new JMenuItem("Create Quiz");
        createNewQuizItem.addActionListener(event -> new CreateQuiz(this));
        fileMenu.add(loadQuizMenuItem);
        fileMenu.add(createNewQuizItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        getContentPane().add(BorderLayout.CENTER, background);
        setSize(400, 500);
        setVisible(true);
    }

    public void loadQuiz(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File savedFile = chooser.getSelectedFile();
        if(!(savedFile.getName().toUpperCase().endsWith(".SER"))){
            System.out.println("This is not a supported file format");
        }
        else{
            try(FileInputStream fs = new FileInputStream(savedFile)){
                ObjectInputStream os = new ObjectInputStream(fs);
                quiz = (Quiz) os.readObject();
                quizPanel = new QuizPanel(quiz);
                background.add(BorderLayout.CENTER, quizPanel);
                background.revalidate();
                
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }
    }
}
