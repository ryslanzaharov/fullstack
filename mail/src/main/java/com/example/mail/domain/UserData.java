package com.example.mail.domain;

public class UserData {

    private final String host = "pop.gmail.com";
    private String userName;
    private String password;

    public UserData(String user, String password) {
        this.userName = user;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
