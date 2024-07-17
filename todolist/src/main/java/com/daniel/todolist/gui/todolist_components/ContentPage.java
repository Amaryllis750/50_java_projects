package com.daniel.todolist.gui.todolist_components;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.stream.*;
import com.daniel.todolist.db_objs.*;

import jiconfont.IconFont;
import jiconfont.swing.IconFontSwing;
import com.daniel.todolist.gui.icons.FontAwesome;

import java.util.*;

public class ContentPage extends JPanel {
    private JTable tasksTable;
    private TableRowSorter<MyTableModel> sorter;
    private MyActionButton importantButton;
    private MyActionButton completeButton;
    private MyActionButton deleteButton;
    private User user;
    private MyTableModel model;
    // setup some icons that will be used in the application
    Icon deleteIcon = IconFontSwing.buildIcon(FontAwesome.TRASH, 18, new Color(240, 68, 77));
    Icon notImportantIcon = IconFontSwing.buildIcon(FontAwesome.STAR, 18, new Color(196, 196, 196));
    Icon importantIcon = IconFontSwing.buildIcon(FontAwesome.STAR, 18, new Color(62, 158, 255));
    Icon completeIcon = IconFontSwing.buildIcon(FontAwesome.CHECK_SQUARE, 18, new Color(0, 230, 0));

    
    public ContentPage(User user) {
        super();
        this.user = user;
        setBackground(Color.white);
        setupContentPage();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    
    public void setupContentPage() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 5, 5));

        importantButton = new MyActionButton(notImportantIcon);
        importantButton.setActionCommand("IMPORTANT");

        completeButton = new MyActionButton(completeIcon);
        completeButton.setActionCommand("COMPLETE");

        deleteButton = new MyActionButton(deleteIcon);
        deleteButton.setActionCommand("DELETE");

        buttonPanel.add(importantButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setBackground(Color.white);

        model = new MyTableModel();
        tasksTable = new JTable(model) {

            ListSelectionModel select = getSelectionModel();

            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                if (select.isSelectedIndex(rowIndex)) {
                    select.removeSelectionInterval(rowIndex, rowIndex);
                } else {
                    select.addSelectionInterval(rowIndex, rowIndex);
                }

                if (select.getSelectedItemsCount() != 0) {
                    importantButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    completeButton.setEnabled(true);

                    // this conditional is to check if the task is already important
                    if (((Task) getValueAt(getSelectedRow(), 0)).getIsImportant() == false) {
                        importantButton.setIcon(notImportantIcon);
                    } else {
                        importantButton.setIcon(importantIcon);
                    }
                } else {
                    importantButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    completeButton.setEnabled(false);
                }

            }
        };
        
        tasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // tasksTable.setCellSelectionEnabled(true);
        tasksTable.setDefaultRenderer(Task.class, new TaskCellRenderer());
        sorter = new TableRowSorter<>(model);
        tasksTable.setRowSorter(sorter);

        // setup the action listeners for the buttons
        importantButton.addActionListener(event -> {
            MyJDBC.commitAction(event, getTask().getTaskId());
            resetModelData();
        });
        completeButton.addActionListener(event -> {
            MyJDBC.commitAction(event, getTask().getTaskId());
            resetModelData();
        });
        deleteButton.addActionListener(event -> {
            MyJDBC.commitAction(event, getTask().getTaskId());
            resetModelData();
        });

        // add the table and the button panel to the panel
        add(new JScrollPane(tasksTable));
        add(buttonPanel);
    }

    // resets the table model's data
    public void resetModelData(){
        model.setData();
    }
    
    public void filterTable(String category) {
        RowFilter<AbstractTableModel, Object> filter = null;

        filter = new RowFilter<AbstractTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends AbstractTableModel, ? extends Object> entry) {
                Task task = (Task) entry.getValue(0);
                if(task.isCompleted()){
                    return false;
                }
                if (category == "ASSIGNED") {
                    return task.getUserId() != task.getAssigned_by();
                } else if (category == "IMPORTANT") {
                    return task.getIsImportant();
                } else {
                    return true;
                }
            }
        };

        sorter.setRowFilter(filter);
    }

    private Task getTask() {
        // this get's the selected task and converts it from the view index to the model
        // index and then returns the final `Task` objects
        int viewRowIndex = tasksTable.getSelectedRow();
        // convert `rowIndex` from view index to model index
        int modelRowIndex = tasksTable.convertRowIndexToModel(viewRowIndex);
        // get the task
        Task task = (Task) tasksTable.getValueAt(modelRowIndex, 0);
        return task;
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Tasks" };
        private Object[][] data = MyJDBC.getTasks(user.getId()).stream()
                .map(element -> new Object[] { element })
                .toArray(Object[][]::new);
        private Class<?>[] columnClasses = { Task.class };

        public String getColumnName(int c) {
            return this.columnNames[c];
        }

        public int getColumnCount() {
            return this.columnNames.length;
        }

        public Object getValueAt(int row, int col) {
            return (Task) data[row][col];
        }

        public int getRowCount() {
            return this.data.length;
        }

        // JTable uses this method to figure out the default renderer for each cell
        public Class<?> getColumnClass(int c) {
            // this is because there is only one column in my table which is the task class;
            return columnClasses[c];
        }

        public boolean isEditable(int row, int column) {
            return false;
        }

        public void setData() {
            this.data = MyJDBC.getTasks(user.getId()).stream()
                    .map(element -> new Object[] { element })
                    .toArray(Object[][]::new);
            fireTableDataChanged();
        }
    }

}

class TaskCellRenderer extends DefaultTableCellRenderer {
    private Font labelFont = new Font("Dialog", Font.PLAIN, 17);

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Task task = (Task) value;
        String text = task.getTaskName() + " | " + task.getDaysLeft();
        setFont(labelFont);
        return super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
    }
}
