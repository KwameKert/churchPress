package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.User;
import com.codeinsyt.churchpressapi.repositories.UserRepository;
import com.codeinsyt.churchpressapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }




    @Override
    public HashMap<String, Object> createUser(User user){

        HashMap<String, Object> responseData = new HashMap<>();
        try {
            User newUser = userRepository.save(user);
            responseAPI(newUser,"User added succesfully",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            responseAPI(null, e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
        return responseData;
    }



    @Override
    public HashMap<String, Object> listUsers(){
        HashMap<String, Object> responseData = new HashMap<>();
        try {
            List<User> allUsers = userRepository.findAll();
            if(allUsers.isEmpty()){
                responseAPI(Collections.EMPTY_LIST,"No Users found",HttpStatus.NO_CONTENT);
                return responseData;
            }
            responseAPI(allUsers,"Listing all users",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            responseAPI(null, e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
        return responseData;
    }

    @Override
    public HashMap<String, Object> updateUser(User user) {
        try {
            Optional<User> userFound = userRepository.findById(user.getId());
            if(!userFound.isPresent()){
                return responseAPI(Collections.EMPTY_LIST,"No Users found",HttpStatus.NO_CONTENT);
            }
            User oldUser = userFound.get();
            oldUser.setRole(user.getRole());
            oldUser.setStatus(user.getStatus());

            User updatedUser  = userRepository.save(oldUser);
            return responseAPI(updatedUser,"User updated",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,"No Users found",HttpStatus.EXPECTATION_FAILED);

        }

    }

    @Override
    public HashMap<String, Object> softDeleteUser(Long id) {
        try {
            Optional<User> userFound = userRepository.findById(id);
            if(!userFound.isPresent()){
                return responseAPI(Collections.EMPTY_LIST,"No Users found",HttpStatus.NO_CONTENT);
            }
            userRepository.UpdateUserStatus(id,"inactive");

            return responseAPI(userFound,"User updated",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,"No Users found",HttpStatus.EXPECTATION_FAILED);

        }


    }

    @Override
    public HashMap<String, Object> hardDeleteUser(Long id) {
        try{
            Optional<User> theUser = userRepository.findById(id);

            if(!theUser.isPresent()){
                return responseAPI(Collections.EMPTY_LIST,"No User found",HttpStatus.NO_CONTENT);
            }

            userRepository.deleteById(id);
            return listUsers();
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,"No Users found",HttpStatus.EXPECTATION_FAILED);
        }

    }


    @Override
    public HashMap<String, Object> getUser(Long id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (!optionalUser.isPresent()) {
                return responseAPI(Collections.EMPTY_LIST,"No Users found",HttpStatus.NO_CONTENT);
            }
            return responseAPI(optionalUser,"User found",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null, e.getMessage(),HttpStatus.EXPECTATION_FAILED);

        }
    }
}
