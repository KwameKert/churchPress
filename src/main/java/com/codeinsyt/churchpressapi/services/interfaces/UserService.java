package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.User;

import java.util.HashMap;

public interface UserService {


    HashMap<String, Object> createUser(User user);

    HashMap<String, Object> updateUser(User user);

    HashMap<String, Object>  softDeleteUser(Long id);

    HashMap<String, Object> hardDeleteUser(Long id);

    HashMap<String, Object> listUsers();

    HashMap<String, Object> getUser(Long id);
    User getUserByUserName(String username);

}
