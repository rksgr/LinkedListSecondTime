package com.example.userregistration2.service;

import com.example.userregistration2.DTO.RegisterDTO;
import com.example.userregistration2.model.JwtRequest;
import com.example.userregistration2.model.UserData;
import java.util.List;

public interface IUserDataService {

    List<UserData> getAllUserData();

    UserData registerNewUser(RegisterDTO registerDTO);

    UserData loginUser(JwtRequest jwtRequest);

}
