package com.daniel.todolist.db_objs;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.awt.*;

public class Task {

    private int taskId;
    private String taskName;
    private Date createdDate;
    private Date deadline;
    private boolean overdue;
    private boolean completed;
    private int userId;
    private int assigned_by;
    private boolean isImportant;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Color taskColor = Color.green;

    public Task(int taskId, String taskName, Date created_date, Date deadline, boolean overdue, boolean completed,
                int userId, int asssigned_by, boolean isImportant){
            this.taskId = taskId;
            this.taskName = taskName;
            this.createdDate = created_date;
            this.deadline = deadline;
            this.overdue =overdue;
            this.completed = completed;
            this.userId = userId;
            this.assigned_by = asssigned_by;
            this.isImportant = isImportant;
        }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getUserId() {
        return userId;
    }

    public int getAssigned_by() {
        return assigned_by;
    }

    public boolean getIsImportant(){
        return this.isImportant;
    }

    public String getDaysLeft(){
        String result;
        LocalDate localCreated = LocalDate.parse(dateFormat.format(createdDate), dtf);
        LocalDate localDeadline = LocalDate.parse(dateFormat.format(deadline), dtf);

        long daysBetween = Duration.between(localCreated.atStartOfDay(), localDeadline.atStartOfDay()).toDays();
        if(daysBetween < 0){
            result = daysBetween + " days overdue";
            // change the task color to red
            taskColor = Color.red;
            return result;
        }
        result = daysBetween + " days left";
        return result;
    }

    @Override 
    public String toString(){
        return this.taskName;
    }

    public void toggleImportant(){
        if(this.isImportant == true){
            this.isImportant = false;
        }
        else{
            this.isImportant = true;
        }
    }
}
