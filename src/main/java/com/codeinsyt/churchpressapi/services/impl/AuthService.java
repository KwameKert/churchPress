package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.AuthenticationRequest;
import com.codeinsyt.churchpressapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {

    public UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
        try{
            com.codeinsyt.churchpressapi.models.User userFound = userRepository.findByUserName(username);
            return new User(userFound.getUserName(), userFound.getPassword(), new ArrayList<>());
        }catch(Exception e){
            System.out.println("Exception here");
            throw new UsernameNotFoundException("user not found");
        }
    }


    //Validating User
    public com.codeinsyt.churchpressapi.models.User isUser(AuthenticationRequest authenticationRequest ){
        HashMap<String, Object> responseData = new HashMap<>();
        try {
            Optional<com.codeinsyt.churchpressapi.models.User> theUser = userRepository.findFirstByUserNameAndPassword(authenticationRequest.getUserName(),authenticationRequest.getPassword());

//            if(!theUser.isPresent()){
//                responseData.put("data", Collections.EMPTY_LIST);
//                response Data.put("message","Invalid Credentials");
//                responseData.put("status", HttpStatus.NO_CONTENT.value());
//                return responseData;
//            }
//            responseData.put("data",theUser);
//            responseData.put("message","User is authenticated");
//            responseData.put("status", HttpStatus.OK.value());
            return theUser.get();
        }catch(Exception e){

            return null;
        }

    //    return responseData;
    }
}
