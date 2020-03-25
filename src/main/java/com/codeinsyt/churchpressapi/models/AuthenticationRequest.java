package com.codeinsyt.churchpressapi.models;

import com.codeinsyt.churchpressapi.utils.EncryptPassword;

public class AuthenticationRequest {

    private String username;
    private String password;



    public AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncryptPassword.hashPassword(password);
    }
}
