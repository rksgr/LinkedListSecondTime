package com.example.userregistration2.service;

import com.example.userregistration2.DTO.RegisterDTO;
import com.example.userregistration2.model.JwtRequest;
import com.example.userregistration2.model.UserData;
import com.example.userregistration2.repository.UserDataRepository;
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

    List<UserData> userDataList = new ArrayList<>();


    // To generate JSON web token-- email used as username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData userData = null;
        if(userDataRepository.findByEmail(email) != null)
        {
            userData = userDataRepository.findByEmail(email);
            return new User(email, userData.getPassword(), new ArrayList<>());
        }
        else
        {
            throw new UsernameNotFoundException("User Name not found!");
        }
        /**
        if(email.equals("zelinsky.volodymyr@gmail.com"))
        {
            return new User("zelinsky.volodymyr@gmail.com", "volodymyr", new ArrayList<>());
        }
        else
        {
            throw new UsernameNotFoundException("User Not Found!!");
        }
         */
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
        String email = jwtRequest.email;
        String password = jwtRequest.password;

        System.out.println("username(email): "+ email+" password: "+ password);
        // load username and password here and do authentication
        // Once authentication is done we generate jwt token
        return userDataRepository.findByEmailAndPassword(email,password);

    }


    // incorporate methods to send mail-- in util package
}

