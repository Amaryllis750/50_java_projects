package com.daniel.todolist.gui.todolist_components;

import javax.swing.*;

import com.daniel.todolist.db_objs.MyJDBC;
import com.daniel.todolist.db_objs.Task;
import com.daniel.todolist.db_objs.User;
import com.daniel.todolist.gui.ToDoList;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import java.util.stream.*;

public class SideBar extends JPanel {
    private Font guiFont = new Font("Dialog", Font.PLAIN, 18);
    private GridBagConstraints gbc = new GridBagConstraints();
    private SideBarButton tasksAssignedToMe;
    private SideBarButton tasks;
    private SideBarButton importantTasks;

    public SideBar() {
        setLayout(new GridBagLayout());
        setupGUI();
    }

    public void setupGUI() {
        setBackground(Color.white);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        // add the first label -> this label denotes a section for tasks that were
        // assigned to you by others
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tasksAssignedToMe = new SideBarButton("Assigned to me");
        tasksAssignedToMe.setName("ASSIGNED");
        tasksAssignedToMe.addActionListener(event -> {
            changeContentPage("ASSIGNED");
            handleButtonPress(event);
        });
        add(tasksAssignedToMe, gbc);

        // this label denotes a section for total tasks that the user has
        gbc.gridy++;
        tasks = new SideBarButton("Tasks");
        tasks.setName("");
        tasks.setActive(true);
        tasks.addActionListener(event -> {
            changeContentPage("");
            handleButtonPress(event);
        });
        add(tasks, gbc);

        // this is the label for the important tasks
        gbc.gridy++;
        importantTasks = new SideBarButton("Important");
        importantTasks.setName("IMPORTANT");
        importantTasks.addActionListener(event -> {
            changeContentPage("IMPORTANT");
            handleButtonPress(event);
        });
        gbc.weighty = 1.0;
        add(importantTasks, gbc);
    }

    // this would handle the button changing colors when they are pressed
    public void handleButtonPress(ActionEvent e) {
        
    }

    public void changeContentPage(String category) {
        // get the main frame of the todolist application
        ToDoList ownerFrame = (ToDoList) SwingUtilities.windowForComponent(SideBar.this);
        ownerFrame.updateContentPage(category);
    }
}
