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
            com.codeinsyt.churchpressapi.models.User userFound = userRepository.findByUserName(username);

            return new User("kwamekert","$2a$10$JQBKiSkGWhhSKTbi1qCpMO8/i0hjwGd9vuc4uy7zGWzEINxNu78V.",new ArrayList<>());
           // return new User(userFound.getUserName(), userFound.getPassword(),new ArrayList<>());
        }catch(Exception e){
            System.out.println("Exception here");
            throw new UsernameNotFoundException("user not found");
        }


    }
}
