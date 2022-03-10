package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.entity.JwtResponse;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.service.BookStoreService;
import com.example.bookstorebackend.service.CustomUserDataService;
import com.example.bookstorebackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDataService customUserDataService;

    @Autowired
    private JwtUtil jwtUtil;

    // Not to be shown to user - push to util
    @PostMapping(value="/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        System.out.println(jwtRequest);
        try{
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmailId(),
                                                            jwtRequest.getPassword()));
            System.out.println("jwt request ");
        }
        catch(UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        // Final area
        UserDetails userDetails = this.customUserDataService.loadUserByUsername(jwtRequest.getEmailId());
        String token =this.jwtUtil.generateToken(userDetails);
        System.out.println("Jwt  token: " + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
