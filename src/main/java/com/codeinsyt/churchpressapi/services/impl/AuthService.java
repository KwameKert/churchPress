package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
            com.codeinsyt.churchpressapi.models.User userFound = userRepository.findFirstByUserName(username);
            return new User(userFound.getUserName(), userFound.getPassword(),new ArrayList<>());
        }catch(Exception e){
            throw new UsernameNotFoundException("user not found");
        }


    }
}
