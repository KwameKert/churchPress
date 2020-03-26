package com.codeinsyt.churchpressapi.models;

import com.codeinsyt.churchpressapi.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class AuthenticationRequest {

    private String userName;
    private String password;



    public AuthenticationRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)  {
        this.password = EncryptPassword.hashPassword(password);
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
