package com.daniel.todolist.gui.todolist_components;

import javax.swing.*;

import com.daniel.todolist.gui.todolist_components.create_task_form.CreateTaskForm;

import java.awt.*;
import java.awt.event.*;

public class AddTask extends JPanel{
    
    public AddTask(){
        setLayout(new BorderLayout());
        setupGUI();
    }

    public void setupGUI(){

        // this is the button to create a new task
        setBackground(Color.white);
        JButton createTaskButton = new JButton("+");
        createTaskButton.setFont(new Font("Dialog", Font.BOLD, 20));
        createTaskButton.setBackground(Color.white);
        createTaskButton.setBorder(null);
        add(BorderLayout.WEST, createTaskButton);

        createTaskButton.addActionListener(event -> {
            new CreateTaskForm((Frame) SwingUtilities.getWindowAncestor(AddTask.this));
        });
    }

}
