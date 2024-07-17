package com.daniel.todolist.gui.todolist_components.create_task_form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.*;

import java.time.format.DateTimeFormatter;

import com.daniel.todolist.gui.ToDoList;
import com.daniel.todolist.db_objs.*;
import com.daniel.todolist.gui.todolist_components.*;

public class CreateTaskForm extends JDialog{
    private GridBagConstraints gbc = new GridBagConstraints();
    private Font textFieldFont = new Font("Dialog", Font.PLAIN, 20);
    private Font labelFont = new Font("Dialog", Font.PLAIN, 14);
    private JTextField taskNameField;
    private DateTextField deadlineField;

    public CreateTaskForm(Frame owner){
        super(owner, true);
        setTitle("Create A New Task");
        setupGUI();
        setVisible(true);
    }

    public void setupGUI(){
        JPanel background = new JPanel(new GridBagLayout());
        

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 15, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        JLabel taskNameLabel = new JLabel("Task Name");
        taskNameLabel.setFont(labelFont);
        background.add(taskNameLabel, gbc);


        gbc.gridy++;
        gbc.insets = new Insets(0, 15, 15, 15);
        taskNameField = new JTextField(20);
        taskNameField.setFont(textFieldFont);
        background.add(taskNameField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 15, 0, 0);
        JLabel deadlineLabel = new JLabel("Task Deadline");
        deadlineLabel.setFont(labelFont);
        background.add(deadlineLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 15, 15, 15);
        deadlineField = new DateTextField("yyyy-MM-dd", new Date());
        deadlineField.setFont(textFieldFont);
        background.add(deadlineField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 15, 15, 15);
        MyButton createTaskButton = new MyButton("Create Task");
        createTaskButton.setFont(textFieldFont);
        createTaskButton.addActionListener(event -> addTask());
        background.add(createTaskButton, gbc);

        getContentPane().add(BorderLayout.CENTER, background);
        pack();
    }

    public void addTask(){
        ToDoList frameOwner = (ToDoList) SwingUtilities.getWindowAncestor(CreateTaskForm.this);
        User user = frameOwner.getUser();
        
        int userId = user.getId();
        String taskName =taskNameField.getText();
        LocalDate localDate = LocalDate.ofInstant(deadlineField.getDate().toInstant(), ZoneId.systemDefault());
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);

        boolean taskCreated = MyJDBC.createTask(taskName, date, userId, userId);
        
        if(taskCreated){
            JOptionPane.showMessageDialog(this, "A new Task has been successfully created");
        }
        else{
            JOptionPane.showMessageDialog(this, "Sorry, there must have been an error in registering your task");
        }

        frameOwner.refreshTable();
    }
}
