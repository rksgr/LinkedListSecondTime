package com.example.userregistration2.controller;

import com.example.userregistration2.DTO.RegisterDTO;
import com.example.userregistration2.DTO.ResponseDTO;
import com.example.userregistration2.model.JwtRequest;
import com.example.userregistration2.model.JwtResponse;
import com.example.userregistration2.model.UserData;
import com.example.userregistration2.service.CustomUserDataService;
import com.example.userregistration2.service.IUserDataService;
import com.example.userregistration2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/userservice")
public class UserController {
// user's email id is taken as the username

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserDataService userDataService;

    @Autowired
    private CustomUserDataService customUserDataService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = {"/get"})
    public ResponseEntity<ResponseDTO> getAllUserDetails()
    {
        List<UserData> userDataList = null;
        //System.out.println("Inside controller class");

        userDataList = userDataService.getAllUserData();
        ResponseDTO responseDTO = new ResponseDTO("Get all user details call success", userDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    // Registration of a new user
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerNewUser(@RequestBody RegisterDTO registerDTO)
    {
        UserData userData = null;
        userData = userDataService.registerNewUser(registerDTO);
        ResponseDTO responseDTO = new ResponseDTO(" New User Registration Success ", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    // For the user to login using email as username and password
    @RequestMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody JwtRequest jwtRequest)
    {
        UserData userData = null;
        ResponseDTO responseDTO = null;
        userData = userDataService.loginUser(jwtRequest);
        if (userData != null)
        {
            responseDTO = new ResponseDTO(" User Login Success ", userData);
        }
        else
        {
            responseDTO = new ResponseDTO(" User Login Not Successful ", userData);
        }
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}
