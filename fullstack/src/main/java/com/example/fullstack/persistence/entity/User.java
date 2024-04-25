package com.example.fullstack.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    @JsonIgnore
    private String user;
    @JsonIgnore
    private String pass;
    @JsonIgnore
    private String email;

    public User(String user, String pass, String email) {
        this.user = user;
        this.pass = pass;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
