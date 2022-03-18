package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.dto.ResetPasswordDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.dto.VerifyUser;
import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.entity.UserData;

import java.util.List;
import java.util.Optional;

public interface ICustomUserDataService {
    List<UserData> getAllUserData();

    UserData registerNewUser(RegisterDTO registerDTO);

    UserData loginUser(JwtRequest jwtRequest);

    ResponseDTO verifyUserCredential(VerifyUser verifyUser);

    void deleteUserByEmailId(String emailId);

    UserData getUserByEmailId(String emailId);

    ResponseDTO forgotPassword(String emailId);

    ResponseDTO resetPassword(String token, String newPasswrd);
}
