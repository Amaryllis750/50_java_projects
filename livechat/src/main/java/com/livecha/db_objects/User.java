package com.livecha.db_objects;

public class User {
    private int userId;
    private String fullname;
    private String username;
    private String password;

    public User(int userId, String fullname, String username, String password){
        this.userId = userId;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    
}
