package com.juandev.taller1;

public class UsersModel {
    private String user,password;

    public UsersModel(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
