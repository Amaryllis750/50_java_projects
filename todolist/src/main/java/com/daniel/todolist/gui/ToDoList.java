package com.daniel.todolist.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import com.daniel.todolist.db_objs.*;
import com.daniel.todolist.gui.todolist_components.*;

public class ToDoList extends JFrame{
    private User user;
    private ContentPage contentPage;

    public ToDoList(User user, String title){
        super(title);
        this.user = user;
        setupGUI();
    }

    public User getUser(){
        return this.user;
    }

    public void setupGUI(){
        setSize(800, 700);
        setVisible(true);
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        // this is the sidebar where the different categories will be shown
        SideBar sidePanel = new SideBar();
        // this is a panel at the bottom of the screen to generate a form to add a new task
        JPanel addNewTask = new AddTask();
        // this is a list of tasks available in the database
        java.util.List<Task> currentTasks = sidePanel.filterTaskList(MyJDBC.getTasks(user.getId()), "");
        // this is the place where the tasks will be shown
        contentPage = new ContentPage(currentTasks);
        // contentPage.setupGUI();


        background.add(BorderLayout.WEST, sidePanel);
        background.add(BorderLayout.CENTER, contentPage);
        background.add(BorderLayout.SOUTH, addNewTask);
        getContentPane().add(BorderLayout.CENTER, background);
    }

    public void updateContentPage(java.util.List<Task> tasks){
        System.out.println("HI there");
    }
}
