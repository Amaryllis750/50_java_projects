package org.daniel.adventurelog.jdbc;

import java.time.LocalDate;

public class DailyTask {
    private int taskId;
    private String taskName;
    private LocalDate createdDate;
    private LocalDate deadlineDate;
    private boolean status;
    private int userId;

    public DailyTask(int taskId, String taskName, String createdDateString, String deadlineDateString, boolean status, int userId){
        this.taskId = taskId;
        this.taskName = taskName;
        this.createdDate = LocalDate.parse(createdDateString);
        this.deadlineDate = LocalDate.parse(deadlineDateString);
        this.status = status;
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public boolean isStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }
}
