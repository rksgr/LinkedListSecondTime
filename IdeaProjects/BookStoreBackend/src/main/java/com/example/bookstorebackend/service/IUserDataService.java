package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.entity.UserData;

import java.util.List;

public interface IUserDataService {
    List<UserData> getAllUserData();

    UserData registerNewUser(RegisterDTO registerDTO);

    UserData loginUser(JwtRequest jwtRequest);
}
