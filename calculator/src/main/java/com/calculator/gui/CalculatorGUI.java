package com.calculator.gui;

import javax.swing.*;

import com.calculator.EquationParser;

import java.awt.*;


public class CalculatorGUI extends JFrame{
    private Font inputFont = new Font("Dialog", Font.PLAIN, 30);
    private JPanel buttonPanel;
    private JTextField inputTextField;
    
    public CalculatorGUI(){
        super("Calculator");
        setupGUI();
    }

    public void setupGUI(){
        JPanel background  = new JPanel();
        background.setLayout(new BorderLayout(5, 5));
        background.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // this is the panel that contains the disabled input field of the calculator
        inputTextField = new JTextField();
        inputTextField.setBackground(Color.white);
        inputTextField.setEnabled(false);
        inputTextField.setFont(inputFont);

        // this is the panel that will contain all the buttons that will be in the calculator;
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        setupButtons();


        background.add(BorderLayout.NORTH, inputTextField);
        background.add(BorderLayout.CENTER, buttonPanel);
        getContentPane().add(BorderLayout.CENTER, background);
        setSize(350, 400);
        setResizable(false);
        // pack();
        setVisible(true);
    }

    // this function is going to setup all the buttons in the button panel
    private void setupButtons(){
        MyButton button7 = new MyButton("7");
        MyButton button8 = new MyButton("8");
        MyButton button9 = new MyButton("9");        
        MyButton button4 = new MyButton("4");
        MyButton button5 = new MyButton("5");
        MyButton button6 = new MyButton("6");
        MyButton button1 = new MyButton("1");
        MyButton button2 = new MyButton("2");
        MyButton button3 = new MyButton("3");
        MyButton button0 = new MyButton("0");

        MyButton addButton = new MyButton("+");
        MyButton subtractButton = new MyButton("-");
        MyButton divideButton = new MyButton("/");
        MyButton multiplyButton = new MyButton("*");

        JButton clearButton = new JButton("C");
        clearButton.addActionListener(event -> inputTextField.setText(""));

        JButton submitButton = new JButton("=");
        submitButton.addActionListener(event -> submit(inputTextField.getText()));

        // add all the buttons to the button panel
        // this is the first row
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(addButton);

        // second row
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(divideButton);

        // third row
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(subtractButton);

        // fourth row
        buttonPanel.add(clearButton);
        buttonPanel.add(button0);
        buttonPanel.add(submitButton);
        buttonPanel.add(multiplyButton);

    }
    

    /*
     * this is an inner class that extends JButton
     * this custom button has an action listener that will change the text of the input box
     * for example 1, 2, 3 buttons
     */
    class MyButton extends JButton{
        public MyButton(String text){
            super(text);
            setName(text);
            addActionListener(event -> inputTextField.setText(inputTextField.getText()+getName()));
        }
    }

    public void clear(){
        inputTextField.setText("");
    }

    public void submit(String s){
        int result = EquationParser.parseString(s);
        inputTextField.setText(Integer.toString(result));
    }
}
