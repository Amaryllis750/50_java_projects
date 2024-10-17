package org.daniel.adventurelog.jdbc;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskJDBC {
    private static final String DB_URL = "jdbc:sqlite:/" + Path.of(System.getProperty("user.home")).resolve("./adventurelog/database/adventurelog.db");


    public static boolean addDailyTask(int userId, String taskName, String deadline) {
        try (Connection con = DatabaseConnectionPool.getDataSource().getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "BEGIN TRANSACTION" +
                            "INSERT INTO daily_tasks(task_name, created_time, deadline_time, status, user_id)" +
                            "VALUES(?, julianday('now'), julianday(?), FALSE, ?)" +
                            "END TRANSACTION"
            );
            statement.setString(1, taskName);
            statement.setString(2, deadline);
            statement.setInt(3, userId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<DailyTask> getDailyTasks(int userId){
        try(Connection con = DatabaseConnectionPool.getDataSource().getConnection()){
            List<DailyTask> dailyTasks = new ArrayList<>();
            PreparedStatement statement = con.prepareStatement(
                    "BEGIN TRANSACTION" +
                            "SELECT (task_id, task_name, time(created_time) AS created_time_string, time(deadline_time) AS deadline_time_string, status) FROM missions" +
                            "WHERE user_id=?"
            );
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int taskId = rs.getInt("task_id");
                String taskName = rs.getString("task_name");
                String createdTime = rs.getString("created_time_string");
                String deadlineTime = rs.getString("deadline_time_string");
                boolean status = rs.getBoolean("status");
                DailyTask task = new DailyTask(taskId, taskName, createdTime, deadlineTime, status, userId);
                dailyTasks.add(task);
            }

            return dailyTasks;

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static boolean addMission(int userId, String taskName, String deadline){
        try(Connection con = DatabaseConnectionPool.getDataSource().getConnection()){
            PreparedStatement statement = con.prepareStatement(
                    "BEGIN TRANSACTION" +
                            "INSERT INTO missions(name, created_date, deadline_date, status, user_id, overdue)" +
                            "VALUES(?, julianday('now'), julianday(?), FALSE, ?, FALSE)" +
                            "END TRANSACTION;"
            );
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated == 1;
        }catch (Exception e){
            try(Connection con = DatabaseConnectionPool.getDataSource().getConnection()){
                PreparedStatement statement = con.prepareStatement(
                        "ROLLBACK;"
                );
                statement.executeUpdate();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}


