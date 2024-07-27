package com.quizapp.gui.createquiz;

import java.util.*;
import java.nio.file.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

import com.quizapp.entities.*;
import com.quizapp.gui.quizcomponents.PaddedButton;

public class CreateQuiz extends JDialog {
    private java.util.List<Question> questions = new ArrayList<>();
    private JTextField questionField, answerField, option1, option2, option3;
    private JPanel otherOptionsPanel;
    private Font fieldFont = new Font("Century Gothic", Font.PLAIN, 18);
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagConstraints gbc1 = new GridBagConstraints();
    private Insets labelInsets = new Insets(0, 15, 0, 0);
    private Insets fieldInsets = new Insets(0, 0, 15, 0);

    public CreateQuiz(JFrame owner) {
        super(owner, "Create new Quiz", true);
        setupGUI();
    }

    public void setupGUI() {
        JPanel background = new JPanel();
        background.setLayout(new GridBagLayout());

        // this is the question field where the question will be imported
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets =  labelInsets;
        JLabel questionLabel = new JLabel("Question");
        background.add(questionLabel, gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        gbc.anchor = GridBagConstraints.CENTER;
        questionField = new JTextField(20);
        background.add(questionField, gbc);

        gbc.gridy++;
        gbc.insets = labelInsets;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel correctAnswerLabel = new JLabel("Correct Option");
        background.add(correctAnswerLabel, gbc);

        gbc.gridy++;
        gbc.insets = fieldInsets;
        gbc.anchor = GridBagConstraints.CENTER;
        answerField = new JTextField(20);
        background.add(answerField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelInsets;
        JLabel otherOptionLabel = new JLabel("Other Options: ");
        background.add(otherOptionLabel, gbc);
        
        // this is the panel where the other incorrect options will be displayed
        gbc.gridy++;
        gbc.insets = fieldInsets;
        gbc.anchor = GridBagConstraints.CENTER;
        otherOptionsPanel = new JPanel();
        otherOptionsPanel.setLayout(new GridBagLayout());
        setupOtherOptionsPanel();
        background.add(otherOptionsPanel, gbc);

        // button panel - this will store three buttons:
        // addOption button, newQuestionButton, saveQuizButton
        gbc.gridy++;
        gbc.weighty = 1.0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.white);
        PaddedButton addOption = new PaddedButton("Add Other Options");
        addOption.addActionListener(event -> addNewOption());
        PaddedButton newQuestionButton = new PaddedButton("New Question");
        newQuestionButton.addActionListener(event -> nextQuestion());
        PaddedButton saveQuiz = new PaddedButton("Save Quiz");
        saveQuiz.addActionListener(event -> saveQuiz());
        background.add(buttonPanel, gbc);

        // sets up all the fonts for input labels
        setupFonts();

        buttonPanel.add(addOption);
        buttonPanel.add(newQuestionButton);
        buttonPanel.add(saveQuiz);
        getContentPane().add(BorderLayout.CENTER, background);

        setSize(450, 500);
        setVisible(true);
    }

    private void setupOtherOptionsPanel() {
        gbc1.gridy = 0;
        gbc1.insets = fieldInsets;
        option1 = new JTextField(20);
        otherOptionsPanel.add(option1, gbc1); // gbc.gridy has already been set to 0

        gbc1.gridy++;
        option2 = new JTextField(20);
        otherOptionsPanel.add(option2, gbc1);

        gbc1.gridy++;
        option3 = new JTextField(20);
        otherOptionsPanel.add(option3, gbc1);
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

    private void setupFonts() {
        questionField.setFont(fieldFont);
        answerField.setFont(fieldFont);
        for (Component com : otherOptionsPanel.getComponents()) {
            JTextField field = (JTextField) com;
            field.setFont(fieldFont);
        }

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
        gbc1.gridy++;
        JTextField newField = new JTextField(20);
        newField.setFont(fieldFont);
        otherOptionsPanel.add(newField, gbc1);
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
            String quizName = JOptionPane.showInputDialog(this, "What is the name of the quiz: ");
            filename = filename + ".ser";
            Path savePath = Paths.get(path, filename);
            File saveFile = new File(savePath.toString());

            // make sure the save the last question just in case
            addQuestion();
            Quiz quiz = new Quiz(quizName);
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
