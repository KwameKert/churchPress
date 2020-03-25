package com.codeinsyt.churchpressapi.services.interfaces;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public interface AuthService {

    public UserDetails loadUserByUsername(String username) ;
}
