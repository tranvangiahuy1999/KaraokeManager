package com.example.admin.myapplication.model;

public class account {
    private String username;
    private static account instance;

    private account(){
    }

    public static account getInstance(){
        if(instance == null){
            instance = new account();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
