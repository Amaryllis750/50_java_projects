package com.daniel.todolist.gui.todolist_components;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import com.daniel.todolist.db_objs.*;
import java.util.*;

public class ContentPage extends JPanel{
    private java.util.List<Task> tasks;
    private JTable tasksTable;

    public ContentPage(java.util.List<Task> tasks){
        super();
        this.tasks = tasks;
        setBackground(Color.white);
    }

    class MyTableModel extends AbstractTableModel{
        private String[] columnNames = {"Tasks"};
        private Object[][] data = {tasks.toArray()};

        public int getColumnCount(){
            return this.columnNames.length;
        }

        public Object getValueAt(int row, int col){
            return this.data[row][col];
        }

        public int getRowCount(){
            return this.data.length;
        }
    }

}



class TaskPanel extends JPanel{
    private Task task;
    private Font labelFont = new Font("Dialog", Font.PLAIN, 17);

    public TaskPanel(Task task){
        super();
        setLayout(new BorderLayout());
        this.task = task;
    }

    public void setupComponents(){
        String taskName = task.getTaskName();
        JLabel taskLabel = new JLabel(taskName);
        taskLabel.setFont(labelFont);
        add(BorderLayout.WEST, taskLabel);

        JPanel buttonPanel = new JPanel();
        JButton complete = new JButton("Complete");
        JButton delete = new JButton("Delete");
        buttonPanel.add(complete);
        buttonPanel.add(delete);
        add(BorderLayout.EAST, buttonPanel);
    }

}


