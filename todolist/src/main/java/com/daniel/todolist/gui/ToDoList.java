package com.daniel.todolist.gui;

import javax.swing.*;

import com.daniel.todolist.db_objs.*;

public class ToDoList extends JFrame{
    private User user;

    public ToDoList(User user){
        setupGUI();
    }

    public void setupGUI(){
        setSize(400, 600);
        setVisible(true);
    }
}
