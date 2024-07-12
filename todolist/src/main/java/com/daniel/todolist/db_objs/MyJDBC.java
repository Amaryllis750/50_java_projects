package com.daniel.todolist.db_objs;

import java.sql.*;
import java.text.DateFormat;

import com.daniel.todolist.db_objs.User;
import java.util.*;
import java.util.Date;

public class MyJDBC {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todolist";
    private static final String usernameInput = "root";
    private static final String passwordInput = "back2SCHOOL~~";
    private static Connection con;

    private static void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, usernameInput, passwordInput);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkIfUsernameExists(String usernameInput) {
        boolean exists = false;

        try {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM todolist.users \n" +
                            "WHERE username = ?");
            statement.setString(1, usernameInput);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                exists = true;
                break;
            }
            return exists;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static ArrayList<Task> getTasks(int currentUserId){
        ArrayList<Task> tasks = new ArrayList<>();
        createConnection();
        int taskId;
        String taskName;
        Date createdDate;
        Date deadline;
        boolean overdue;
        boolean completed;
        int userId;
        int assignedBy;
        boolean isImportant;

        try{
            PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM todolist.tasks\n" + 
                "WHERE userId=?"
            );
            statement.setInt(1, currentUserId);
            ResultSet tasksResultSet = statement.executeQuery();
            while(tasksResultSet.next())
            {
                taskId = tasksResultSet.getInt("task_id");
                taskName = tasksResultSet.getString("task_name");
                createdDate = tasksResultSet.getDate("created_date");
                deadline = tasksResultSet.getDate("deadline");
                overdue = tasksResultSet.getBoolean("overdue");
                completed = tasksResultSet.getBoolean("completed");
                userId = tasksResultSet.getInt("userId");
                assignedBy = tasksResultSet.getInt("assigned_by");
                isImportant = tasksResultSet.getBoolean("important");

                Task task = new Task(taskId, taskName, createdDate, deadline, overdue, completed, userId, assignedBy, isImportant);
                tasks.add(task);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tasks;
    }

    public static boolean createTask(String name, String date, int userId, int assignedUserId){
        createConnection();
        
        try{
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO todolist.tasks (task_name, created_date, deadline, overdue, completed, userId, assigned_by) VALUES("+
                "?, CURRENT_DATE, ?, FALSE, FALSE, ?, ?)"
            );
            statement.setString(1, name);
            statement.setString(2, date);
            statement.setInt(3, userId);
            statement.setInt(4, assignedUserId);
            
            statement.execute();
            System.out.println("Task has been successfully created");
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerUsers(String nameInput, String emailInput, String usernameInput,
            String passwordInput) {
        // setup the connection to the database
        createConnection();

        if (!checkIfUsernameExists(usernameInput)) {
            String query = "INSERT INTO todolist.users (fullname, email, username, password) VALUES(?, ?, ?, ?)";
            try {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, nameInput);
                statement.setString(2, emailInput);
                statement.setString(3, usernameInput);
                statement.setString(4, passwordInput);

                statement.execute();
                return true;
            } catch (SQLException e) {
                System.out.println("there must have been error trying to register this user");
                e.printStackTrace();
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static User loginUsers(String usernameInput, String passwordInput) {
        // setup the connection to the database
        createConnection();
        int id = 0;
        String username = "";
        String fullname = "";
        String email = "";
        String password = "";

        String query = "SELECT * FROM todolist.users WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM todolist.users \n" +
                            "WHERE username=? AND password = ?");
            statement.setString(1, usernameInput);
            statement.setString(2, passwordInput);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("userID");
                username = rs.getString("username");
                fullname = rs.getString("fullname");
                email = rs.getString("email");
                password = rs.getString("password");

                User user = new User(id, fullname, username, email, password);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
