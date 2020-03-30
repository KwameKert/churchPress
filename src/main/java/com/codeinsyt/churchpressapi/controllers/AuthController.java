package com.codeinsyt.churchpressapi.controllers;


import com.codeinsyt.churchpressapi.models.AuthenticationRequest;
import com.codeinsyt.churchpressapi.models.AuthenticationResponse;
import com.codeinsyt.churchpressapi.models.User;
import com.codeinsyt.churchpressapi.services.impl.AuthService;
import com.codeinsyt.churchpressapi.services.interfaces.UserService;
import com.codeinsyt.churchpressapi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/")
public class AuthController {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        HashMap<String, Object> responseData = new HashMap<>();
        try{
           if(authService.isUser(authenticationRequest) != null){
               responseData.put("user",authService.isUser(authenticationRequest));
               responseData.put("message","User logged in");
               responseData.put("jwt",getJwt(authenticationRequest.getUserName(),authenticationRequest.getPassword()));
               return new ResponseEntity<>(responseData, HttpStatus.OK);

           }

           responseData.put("data",null);
           responseData.put("message","Invalid Credentials");
           responseData.put("status",HttpStatus.EXPECTATION_FAILED);
           return new ResponseEntity<>(responseData,HttpStatus.EXPECTATION_FAILED);

        }catch( BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }




    }


    String getJwt(String userName, String password)throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        }catch( BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = authService.loadUserByUsername(userName);

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return jwt;
    }

}
