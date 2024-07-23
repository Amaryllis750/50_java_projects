package com.quizapp.gui.createquiz;

import java.util.*;
import java.nio.file.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

import com.quizapp.entities.*;

public class CreateQuiz extends JDialog {
    private java.util.List<Question> questions = new ArrayList<>();
    private JTextField questionField, answerField, option1, option2, option3;
    private JPanel contentPanel, otherOptionsPanel;

    public CreateQuiz(JFrame owner) {
        super(owner, "Create new Quiz", true);
        setupGUI();
    }

    public void setupGUI() {
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // this is the question field where the question will be imported
        questionField = new JTextField(20);
        answerField = new JTextField(20);

        contentPanel = new JPanel();
        contentPanel.add(questionField);
        contentPanel.add(answerField);

        otherOptionsPanel = new JPanel();
        option1 = new JTextField();
        option2 = new JTextField();
        option3 = new JTextField();
        otherOptionsPanel.add(option1);
        otherOptionsPanel.add(option2);
        otherOptionsPanel.add(option3);

        // button panel - this will store three buttons:
        // addOption button, newQuestionButton, saveQuizButton
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton addOption = new JButton("Add Other Options");
        addOption.addActionListener(event -> addNewOption());
        JButton newQuestionButton = new JButton("New Question");
        newQuestionButton.addActionListener(event -> nextQuestion());
        JButton saveQuiz = new JButton("Save Quiz");
        saveQuiz.addActionListener(event -> saveQuiz());
        buttonPanel.add(addOption);
        buttonPanel.add(newQuestionButton);
        buttonPanel.add(saveQuiz);

        background.add(contentPanel);
        background.add(otherOptionsPanel);
        background.add(buttonPanel);
        getContentPane().add(BorderLayout.CENTER, background);

        setSize(450, 500);
        pack();
        setVisible(true);
    }

    private void addQuestion() {
        String question = questionField.getText();
        String answer = answerField.getText();
        Component[] optionComponents = otherOptionsPanel.getComponents();
        String[] options = new String[optionComponents.length];
        for (int i = 0; i < optionComponents.length; i++) {
            JTextField optionField = (JTextField) optionComponents[i];
            options[i] = optionField.getText();
        }
        // create a new question object
        Question newQuestion = new Question(question, answer, options);
        questions.add(newQuestion);
    }

    private void nextQuestion() {
        if (checkInput()) {
            addQuestion();
            // clear all the inputs from all the textfields
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "You cannot leave an input blank");
        }
    }

    private boolean checkInput() {
        // if the question or answer field is empty
        if ("".equals(questionField.getText()) || "".equals(answerField.getText())) {
            return false;
        }
        Component[] components = otherOptionsPanel.getComponents();
        for (Component com : components) {
            JTextField option = (JTextField) com;
            if ("".equals(option.getText())) {
                return false;
            }
        }
        return true;
    }

    private void addNewOption() {
        otherOptionsPanel.add(new JTextField());
        revalidate();
        repaint();
    }

    private void saveQuiz() {
        JFileChooser chooser = new JFileChooser();
        String path = "";
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = chooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();

            String filename = JOptionPane.showInputDialog(this, "Save Quiz Name as: ");
            filename = filename + ".ser";
            Path savePath = Paths.get(path, filename);
            File saveFile = new File(savePath.toString());
            
            // make sure the save the last question just in case
            addQuestion();
            Quiz quiz = new Quiz("Quiz");
            quiz.setQuestions(questions);

            try (FileOutputStream fs = new FileOutputStream(saveFile)) {
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(quiz);
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage());
                dispose();
            }

            JOptionPane.showMessageDialog(this, "Quiz has been successfully saved");
            questions.clear();
            dispose();

        }
    }

    private void clear() {
        questionField.setText("");
        answerField.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        otherOptionsPanel.removeAll();
        otherOptionsPanel.add(option1);
        otherOptionsPanel.add(option2);
        otherOptionsPanel.add(option3);
        revalidate(); // tells the layout manager to recalculate the layout
        repaint(); // this tells swing that an area of the window is dirty
    }
}
