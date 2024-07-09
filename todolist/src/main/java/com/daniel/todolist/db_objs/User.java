package com.daniel.todolist.db_objs;

public class User {
    private String fullname;
    private String username;
    private String email;
    private String password;

    public User(String fname, String uname, String email, String pwd){
        this.fullname = fname;
        this.username = uname;
        this.email = email;
        this.password = pwd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
