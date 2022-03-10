package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDataService implements IUserDataService, UserDetailsService {

    @Autowired
    public UserDataRepository userDataRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        UserData userData = null;
        if(userDataRepository.findByEmailId(emailId) != null){
            userData =userDataRepository.findByEmailId(emailId);
            return new User(emailId, userData.getPassword(),new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User with username "+ emailId + " not found");
        }
    }

    @Override
    public List<UserData> getAllUserData() {
        System.out.println("Inside service class --- get all user data");
        return userDataRepository.findAll();
    }

    // For registration of a user, using certain inputs as credential
    @Override
    public UserData registerNewUser(RegisterDTO registerDTO){
        System.out.println("Inside service class --- register new user");
        UserData userData = null;
        userData = new UserData(registerDTO);
        System.out.println("Registered the new user: " + userData.toString());
        return userDataRepository.save(userData);
    }

    // For login of a user, using email and password as the credentials

    @Override
    public UserData loginUser(JwtRequest jwtRequest){
        System.out.println("Inside service class --- login new user");
        // Match email and password
        String email = jwtRequest.emailId;
        String password = jwtRequest.password;

        System.out.println("username(email): "+ email+" password: "+ password);
        // load username and password here and do authentication
        // Once authentication is done we generate jwt token
        return userDataRepository.findByEmailIdAndPassword(email,password);
    }

}
