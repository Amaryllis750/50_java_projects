package org.daniel.adventurelog.jdbc;

public class User {
    private String password;
    private String email;
    private int user_id;

    public User(int id, String email, String password){
        this.email = email;
        this.password = password;
        this.user_id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
