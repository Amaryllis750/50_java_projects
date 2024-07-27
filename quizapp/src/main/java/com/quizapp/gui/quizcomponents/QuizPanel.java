package com.quizapp.gui.quizcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import com.quizapp.entities.*;

public class QuizPanel extends JPanel {
    private Quiz quiz;
    // the integers in the list represent the correctness of each answer to each question 1 - correct, 0 - fail
    private int[] scores;
    private Font resultFont = new Font("Dubai", Font.BOLD, 45);
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel questionLabel;
    private java.util.List<Question> questions;
    private int currentQuestionNumber;
    private Question currentQuestion;
    private JPanel optionsPanel, buttonPanel;
    private PaddedButton prevButton, nextButton;
    private Font questionFont = new Font("Dubai", Font.PLAIN, 24);
    private Insets buttonInsets = new Insets(10, 10, 10, 10);

    public QuizPanel(Quiz quiz) {
        this.quiz = quiz;
        questions = quiz.getQuestions();
        currentQuestionNumber = 0;
        scores = new int[questions.size()];
        setupGUI();
    }

    public void setupGUI() {
        setLayout(new BorderLayout(10, 10));
        // get the currentquestion number
        currentQuestion = questions.get(currentQuestionNumber);
        questionLabel = new JLabel(currentQuestion.getQuestion());
        customizeQuestionLabel();// customize the question label

        // this is where the options will be placed
        optionsPanel = new JPanel(new GridBagLayout());
        setupOptionsPanel();

        // this panel is where the previous and next buttons will be stored
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        setupButtonPanel();

        add(BorderLayout.NORTH, questionLabel);
        add(BorderLayout.CENTER, optionsPanel);
        add(BorderLayout.SOUTH, buttonPanel);
    }

    private void setupOptionsPanel() {
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.ipadx = 60;
        gbc.ipady = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // go through each option for the question and place them in buttons
        for (String answer : currentQuestion.getOptions()) {
            OptionButton option = new OptionButton(answer);
            optionsPanel.add(option, gbc);
            gbc.gridy++;
        }
        // go through all the buttons that have been added to the options panel and give them actionlisteners
        // this action listener is responsible for the scoring in the quiz
        Component[] optionButtons = optionsPanel.getComponents();
        for(Component com : optionButtons){
            OptionButton btn = (OptionButton) com;
            btn.addActionListener(event -> {
                toggleButtons(event, optionButtons);
                if(btn.getText() == currentQuestion.getAnswer()){
                    scores[currentQuestionNumber] = 1;
                }
                else{
                    scores[currentQuestionNumber] = 0;
                }
            });
        }
        
    }

    private void setupButtonPanel(){
        prevButton = new PaddedButton("Previous", buttonInsets);
        prevButton.addActionListener(event -> goToPrevious());

        if(currentQuestionNumber == 0){
            prevButton.setEnabled(false);
        }

        if(currentQuestionNumber == questions.size()-1){
            nextButton = new PaddedButton("Submit", buttonInsets);
            nextButton.addActionListener(event -> finishQuiz());
        }
        else{
            nextButton = new PaddedButton("Next", buttonInsets);
            nextButton.addActionListener(event -> goToNext());
        }

        buttonPanel.add(BorderLayout.WEST, prevButton);
        buttonPanel.add(BorderLayout.EAST, nextButton);
        
    }

    private void customizeQuestionLabel(){
        questionLabel.setFont(questionFont);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    // this function toggles the background colors for the buttons, making sure the selected button has a 
    // particular background color
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

    private void goToNext(){
        currentQuestionNumber++;
        System.out.println(currentQuestionNumber);
        setCurrentQuestion(currentQuestionNumber);
    }

    private void goToPrevious(){
        currentQuestionNumber--;
        System.out.println(currentQuestionNumber);
        setCurrentQuestion(currentQuestionNumber);
    }

    private void setCurrentQuestion(int num){
        currentQuestion = questions.get(num);
        questionLabel.setText(currentQuestion.getQuestion());
        optionsPanel.removeAll();
        buttonPanel.removeAll();
        setupButtonPanel();
        setupOptionsPanel();
        revalidate();
        repaint();
    }

    private void finishQuiz(){
        // get the total scores
        int totalScore = sum(scores);

        JLabel scoreLabel = new JLabel(String.format("%d / %d", totalScore, questions.size()), SwingConstants.CENTER);
        scoreLabel.setFont(resultFont);
        // scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        double percentage = ((double) totalScore / questions.size()) * 100;

        JLabel percentageLabel = new JLabel(String.format("%.2f%%", percentage), SwingConstants.CENTER);
        // percentageLabel.setHorizontalAlignment(JLabel.CENTER);
        percentageLabel.setFont(resultFont);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1));
        
        resultPanel.add(scoreLabel);
        resultPanel.add(percentageLabel);

        removeAll();
        add(BorderLayout.CENTER, resultPanel);
        revalidate();
        repaint();

    }

    private int sum(int[] array){
        Integer[] integerArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Integer total = Arrays.asList(integerArray).stream().reduce(0, (a, b) -> a + b);
        return total;
    }
}
