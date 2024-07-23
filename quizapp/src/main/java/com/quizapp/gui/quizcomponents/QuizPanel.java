package com.quizapp.gui.quizcomponents;

import javax.swing.*;
import javax.swing.text.html.Option;

import java.awt.*;
import java.awt.event.*;

import com.quizapp.entities.*;

public class QuizPanel extends JPanel {
    private Quiz quiz;
    private int score;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel questionLabel;
    private java.util.List<Question> questions;
    private int currentQuestionNumber;
    private Question currentQuestion;
    private JPanel optionsPanel;

    public QuizPanel(Quiz quiz) {
        this.quiz = quiz;
        questions = quiz.getQuestions();
        currentQuestionNumber = 0;
        score = 0;
        setupGUI();
    }

    public void setupGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        currentQuestion = questions.get(currentQuestionNumber);
        questionLabel = new JLabel(currentQuestion.getQuestion());

        optionsPanel = new JPanel(new GridBagLayout());
        setupOptionsPanel();

        add(questionLabel);
        add(optionsPanel);
    }

    private void setupOptionsPanel() {
        gbc.gridy = 0;
        for (String answer : currentQuestion.getOptions()) {
            OptionButton option = new OptionButton(answer);
            optionsPanel.add(option, gbc);
            gbc.gridy++;
        }
        // go through all the buttons that have been added to the options panel and give them actionlisteners
        Component[] optionButtons = optionsPanel.getComponents();
        for(Component com : optionButtons){
            OptionButton btn = (OptionButton) com;
            btn.addActionListener(event -> {
                toggleButtons(event, optionButtons);
                if(btn.getNumberOfTimesClicked() == 0 && btn.getText() == currentQuestion.getAnswer()){
                    score += 1;
                }
                btn.incrementNumberOfTimesClicked();
            });
        }
        
    }

    private void toggleButtons(ActionEvent e, Component[] buttons){
        OptionButton sourceButton = (OptionButton) e.getSource();
        for(Component com : buttons){
            OptionButton btn =(OptionButton) com;
            if (btn == sourceButton){
                btn.setSelected(true);
            }
            else{
                btn.setSelected(false);
            }
        }
        optionsPanel.revalidate();
    }
}
