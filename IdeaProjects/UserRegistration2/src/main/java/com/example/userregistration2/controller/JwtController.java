package com.example.userregistration2.controller;

import com.example.userregistration2.model.JwtRequest;
import com.example.userregistration2.model.JwtResponse;
import com.example.userregistration2.service.CustomUserDataService;
import com.example.userregistration2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDataService customUserDataService;
    @RequestMapping("/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);

        try{
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),
                            jwtRequest.getPassword()));
            System.out.println("jwtrequest. get username: "+ jwtRequest.getEmail());
        }
        catch(UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Credentials Invalid!");
        }
        catch(BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        // final area
        UserDetails userDetails = this.customUserDataService.loadUserByUsername(jwtRequest.getEmail());

        // call generate token method
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("Java web token: "+ token);

        //{"token": "value"}
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
