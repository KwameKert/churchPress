package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.User;
import com.codeinsyt.churchpressapi.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {


    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }


    @Override
    public HashMap<String, Object> createUser(User user) {
        return null;
    }

    @Override
    public HashMap<String, Object> updateUser(User user) {
        return null;
    }

    @Override
    public HashMap<String, Object> softDeleteUser(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> hardDeleteUser(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> listUsers() {
        return null;
    }

    @Override
    public HashMap<String, Object> getUser(Long id) {
        return null;
    }
}
