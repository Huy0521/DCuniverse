package com.example.dcuniverse;

import java.io.Serializable;

public class User implements Serializable {
    String account;
    String Email;
    String password;

    public User(String account, String email, String password) {
        this.account = account;
        Email = email;
        this.password = password;
    }

    public User(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User(){}

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
