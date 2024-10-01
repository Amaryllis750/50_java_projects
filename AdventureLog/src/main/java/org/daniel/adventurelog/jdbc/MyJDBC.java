package org.daniel.adventurelog.jdbc;

import java.nio.file.Path;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MyJDBC {
    private static Connection connection;
    private static final String DB_URL =  "jdbc:sqlite:/" + Path.of(System.getProperty("user.home")).resolve(".adventurelog/database/adventurelog.db");

    private static void makeConnection(){
        try {
            connection = DriverManager.getConnection(MyJDBC.DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Boolean, String> createUser(User user){
        // make the connection to the database
        makeConnection();
        Map<Boolean, String> result = new HashMap<>();

        if(checkIfEmailExists(user.getEmail())){
            result.put(false, "The user already exists");
            return result;
        }

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(email, password) VALUES (?, ?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            int updatedRows = statement.executeUpdate();

            if(updatedRows == 1){
                result.put(true, "");
            }
            else{
                result.put(false, "There must have been a problem creating a user");
            }
            return result;
        } catch (SQLException e) {
            result.put(false, e.getMessage());
            return result;
        }
    }

    private static boolean checkIfEmailExists(String email){
        makeConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users \n" +
                            "WHERE email=?"
            );
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User loginUser(String email, String password){
        makeConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users \n" +
                            "WHERE email=? AND password=?"
            );
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return new User(email, password);
            }

            return null;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
