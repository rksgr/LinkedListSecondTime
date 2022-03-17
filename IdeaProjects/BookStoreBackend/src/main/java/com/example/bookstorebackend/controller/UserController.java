package com.example.bookstorebackend.controller;


import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.dto.VerifyUser;
import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.service.CustomUserDataService;
import com.example.bookstorebackend.service.ICustomUserDataService;
import com.example.bookstorebackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/userservice")
public class UserController {
// user's email id is taken as the username

    //@Autowired
    //private AuthenticationManager authenticationManager;

    @Autowired
    private ICustomUserDataService userDataService;

    @Autowired
    private CustomUserDataService customUserDataService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Fetch the details of all the users
     * @return  UserData list
     */
    @RequestMapping(value = {"/get"})
    public ResponseEntity<ResponseDTO> getAllUserDetails()
    {
        List<UserData> userDataList = null;
        userDataList = userDataService.getAllUserData();
        ResponseDTO responseDTO = new ResponseDTO("Get all user details call success", userDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *  Fetch the details of a user with particular email id
     * @return userdata
     */
    @RequestMapping(value = {"/getByEmailId/{emailId}"})
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId)
    {
        UserData userData = null;
        System.out.println("Insid controller");
        userData = userDataService.getUserByEmailId(emailId);
        ResponseDTO responseDTO = new ResponseDTO("Get user by email Id call success", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Register a new user and enter the details into database
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerNewUser(@RequestBody RegisterDTO registerDTO)
    {
        UserData userData = null;
        userData = userDataService.registerNewUser(registerDTO);
        ResponseDTO responseDTO = new ResponseDTO(" New User Registration Success ", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    /**
     * Login to the Bookstore application using email as username and password
     * @param jwtRequest
     * @return Navigation to the Bookstore home page on success
     */
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
            responseDTO = new ResponseDTO(" Oops! User Login Not Successful! ", userData);
        }
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    /**
     * Verify the credentials of a user
     * @param verifyUser (a dto object containing emailid and otp)
     * @return  UserData containing all the details of a user
     */
    @PostMapping("/verify")
    public ResponseEntity<ResponseDTO> verifyUserCredential(@RequestBody VerifyUser verifyUser){
        ResponseDTO responseDTO = null;
        responseDTO = userDataService.verifyUserCredential(verifyUser);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    /**
     * Delete the details of a user with a given email id
     * @param emailId
     * @return void
     */
    @RequestMapping(value = {"/deleteByEmailId/{emailId}"})
    public ResponseEntity<ResponseDTO> deleteUserByEmailId(@PathVariable("emailId") String emailId)
    {
        userDataService.deleteUserByEmailId(emailId);
        ResponseDTO responseDTO = new ResponseDTO("Delete user details call success of user with email id ", emailId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}