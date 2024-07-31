package com.livecha.db_objects;

import java.sql.*;

public class MyJDBC {
    private static String DB_URL = "jdbc:mysql://localhost:3306/livechat";
    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "back2SCHOOL~~";
    private static Connection con;

    private static void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean checkDuplicateUsername(String username){
        try {
            PreparedStatement statment = con.prepareStatement(
                "SELECT * FROM livechat.users \n" +
                "   WHERE username = ?"
            );
            statment.setString(1, username);
            ResultSet rs = statment.executeQuery();

            while(rs.next()){
                // this means that the username already exists
                return true;
            }
            // this means that the username does not exists
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean insertUsers(String fullnameInput, String usernameInput, String passwordInput){
        createConnection();
        if(!(checkDuplicateUsername(usernameInput))){
            try {
                PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO livechat.users (fullname, username, password ) VALUES (\n" +     
                    "?, ?, ?)"
                );
                statement.setString(1, fullnameInput);
                statement.setString(2, usernameInput);
                statement.setString(3, passwordInput);
                
                int rowsAffected = statement.executeUpdate();
                if(rowsAffected == 1){
                    return true;
                }
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            return false;
        }
        
    }

    public static User loginUser(String usernameInput, String passwordInput){
        int userId;
        String username, password, fullname;
        createConnection();
        try {
            PreparedStatement statement = con.prepareStatement(
                "SELECT * FROM livechat.users \n" + 
                    "WHERE username=? AND password=?"
            );
            statement.setString(1, usernameInput);
            statement.setString(2, passwordInput);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                userId = rs.getInt("user_id");
                fullname = rs.getString("fullname");
                username = rs.getString("username");
                password = rs.getString("password");

                return new User(userId, fullname, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
