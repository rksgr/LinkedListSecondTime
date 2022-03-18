package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.dto.ResetPasswordDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.dto.VerifyUser;
import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.exception.InvalidTokenException;
import com.example.bookstorebackend.exception.UserNotFoundException;
import com.example.bookstorebackend.repository.UserDataRepository;
import com.example.bookstorebackend.util.EmailSenderUtil;
import com.example.bookstorebackend.util.JwtUtil;
import com.example.bookstorebackend.util.OtpGenerationUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

/**
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
 */
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDataService implements ICustomUserDataService {

    @Autowired
    public UserDataRepository userDataRepository;

    @Autowired
    public OtpGenerationUtil otpGenerationUtil;

    @Autowired
    public EmailSenderUtil emailSenderUtil;

    @Autowired
    public JwtUtil jwtUtil;

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

        // check if this user already exists in database
        userDataRepository.save(userData);

        //String token = myToken.createToken(user.getUser_id());

        // Generate otp and send it to the registered email
        int otp = otpGenerationUtil.generateOTP(registerDTO.emailId);
        emailSenderUtil.sendOTPMessage(registerDTO.emailId, "Registration OTP","Token = " +
                "Your OTP = "+otp);
        System.out.println("Registered the new user: " + userData.toString());
        return userDataRepository.save(userData);
    }

    // For login of a user, using email and password as the credentials
    @Override
    public UserData loginUser(JwtRequest jwtRequest){
        // Match email and password
        String email = jwtRequest.emailId;
        String password = jwtRequest.password;

        System.out.println("username(email): "+ email+" password: "+ password);
        // load username and password here and do authentication
        // Once authentication is done we generate jwt token
        return userDataRepository.findByEmailIdAndPassword(email,password);
    }

    // Verify the user credentials using OTP sent to her email
    @Override
    public ResponseDTO verifyUserCredential(VerifyUser verifyUser) {
        UserData userData = null;
        System.out.println("Inside service class method verifyUserCredentials.");
        // The user details are already present in the database
        userData = userDataRepository.findByEmailId(verifyUser.getEmailId());
        System.out.println("Inside service class method verifyUserCredentials." + verifyUser.getEmailId());
        if(userData != null){
            // OTP stored in cache(LHS) should match with OTP passed in request body (RHS)
            System.out.println("Inside service class method verifyUserCredentials ---- user data not null ");

            if(otpGenerationUtil.getOTP(verifyUser.getEmailId()) == verifyUser.getOtp()){
                System.out.println("Inside service class method verifyUserCredentials ---- user data not null -- otp verified");
                userData.setVerify(true);
                userDataRepository.save(userData);
                return new ResponseDTO("User Verified Successfully",userData);
            }
        }
        return new ResponseDTO("OTP entered is wrong",null);
    }

    // Delete the details of a user given the emailid of the user
    @Override
    public void deleteUserByEmailId(String emailId) {
        UserData userData = null;
        userData = userDataRepository.findByEmailId(emailId);

        if(userData != null){
            System.out.println("The user exists");
            userDataRepository.delete(userData);
        }
        System.out.println("The user does not exist");
    }

    // Get the details of a user with a given email id
    @Override
    public UserData getUserByEmailId(String emailId) {
        UserData userData = null;
        userData = userDataRepository.findByEmailId(emailId);
        return userData;
    }

    // Sends the password to the email id of the user
    @Override
    public ResponseDTO forgotPassword(String emailId) {
        UserData userData = null;
        userData = userDataRepository.findByEmailId(emailId);

        if (userData == null){
            String msg = "The given emailId entered is not registered.";
            return new ResponseDTO(msg,null);
        }
        else if (userData != null){
            String pwd = userData.getPassword();
            emailSenderUtil.sendOTPMessage("r_kjha@yahoo.com",
                                            "Your password is ", pwd);
            String msg = "Your password has been sent to your registered emailId"+ emailId;
            return new ResponseDTO(msg,null);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public ResponseDTO resetPassword(String token, String PwdNew) {
        String emailId = jwtUtil.decodeToken(token);
        if(emailId == null ){
            throw new InvalidTokenException("The given token is NOT valid!");
        }
        else if(emailId != null){
            UserData userData = userDataRepository.findByEmailId(emailId);

            if (userData == null){
                throw new UserNotFoundException("The given user is NOT registered!");
            }
            else if(userData != null){
                userData.setPassword(PwdNew);
                userDataRepository.save(userData);
                return new ResponseDTO("Password has been updated successfully for ",
                        userData.getFirstName()+ " "+ userData.getLastName() + "!");
            }
        }
        return null;
    }
}