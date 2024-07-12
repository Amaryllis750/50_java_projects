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

public class SideBar extends JPanel{
    private Font guiFont = new Font("Dialog", Font.PLAIN, 18);
    private GridBagConstraints gbc = new GridBagConstraints();

    public SideBar(){
        setLayout(new GridBagLayout());
        setupGUI();
    }   

    public void setupGUI(){
        setBackground(Color.white);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        // add the first label -> this label denotes a section for tasks that were assigned to you by others
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton tasksAssignedToMe = new SideBarButton("Assigned to me");
        tasksAssignedToMe.setName("ASSIGNED");
        tasksAssignedToMe.addActionListener(event -> changeContentPage("ASSIGNED"));
        add(tasksAssignedToMe, gbc);

        // this label denotes a section for total tasks that the user has
        gbc.gridy++;
        JButton tasks = new SideBarButton("Tasks");
        tasks.setName("");
        tasks.addActionListener(event -> changeContentPage(""));
        add(tasks, gbc);

        // this is the label for the important tasks
        gbc.gridy++;
        JButton importantTasks = new SideBarButton("Important");
        importantTasks.setName("IMPORTANT");
        importantTasks.addActionListener(event -> changeContentPage("IMPORTANT"));
        gbc.weighty = 1.0;
        add(importantTasks, gbc);
    }

    public java.util.List<Task> filterTaskList(java.util.List<Task> tasks, String category){
        // initialize a list of filtered tasks
        java.util.List<Task> filteredTasks;

        // depending on the category passed into the function, filter the list according to these conditions
        if(category == "ASSIGNED"){
            filteredTasks = tasks.stream().filter((task) -> task.getUserId() != task.getAssigned_by()).collect(Collectors.toList());
        }
        else if(category == "IMPORTANT"){
            filteredTasks = tasks.stream().filter(Task::getIsImportant).collect(Collectors.toList());
        }
        else{
            // if there is no condition just return all the tasks
            filteredTasks = tasks;
        }
        return filteredTasks;
    }

    public void changeContentPage(String category){
        // get the main frame of the todolist application
        ToDoList ownerFrame = (ToDoList) SwingUtilities.windowForComponent(SideBar.this);
        // get the user associated with the todolist application
        User user = ownerFrame.getUser();
        // get all the tasks
        java.util.List<Task> filteredTasks = filterTaskList(MyJDBC.getTasks(user.getId()), category);
        // each time this function is called, the main frame to update the panel where the tasks are shown
        ownerFrame.updateContentPage(filteredTasks);
    }
}
