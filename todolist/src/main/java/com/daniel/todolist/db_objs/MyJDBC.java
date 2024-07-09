package com.daniel.todolist.db_objs;

import java.sql.*;

import com.daniel.todolist.db_objs.User;

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
                username = rs.getString("username");
                fullname = rs.getString("fullname");
                email = rs.getString("email");
                password = rs.getString("password");
            }

            User user = new User(fullname, username, email, password);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
