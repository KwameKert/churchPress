package com.codeinsyt.churchpressapi.controllers;


import com.codeinsyt.churchpressapi.models.AuthenticationRequest;
import com.codeinsyt.churchpressapi.models.AuthenticationResponse;
import com.codeinsyt.churchpressapi.services.impl.AuthService;
import com.codeinsyt.churchpressapi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class AuthController {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        System.out.println(authenticationRequest);
        try{
           // authService.checkCredentials()
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        }catch( BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        System.out.println("Passed the try");
        final UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//9351b6661730af2a8977f015daa60d3c

    }

}
