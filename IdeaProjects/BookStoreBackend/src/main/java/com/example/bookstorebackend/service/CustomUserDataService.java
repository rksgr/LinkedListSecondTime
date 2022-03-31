package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.dto.UserLoginDTO;
import com.example.bookstorebackend.dto.VerifyUser;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.exception.InvalidTokenException;
import com.example.bookstorebackend.exception.UserNotFoundException;
import com.example.bookstorebackend.repository.UserDataRepository;
import com.example.bookstorebackend.util.EmailSenderUtil;
import com.example.bookstorebackend.util.JwtTokenUtil;
import com.example.bookstorebackend.util.OtpGenerationUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public JwtTokenUtil jwtTokenUtil;

    @Override
    public List<UserData> getAllUserData() {
        System.out.println("Inside service class --- get all user data");
        return userDataRepository.findAll();
    }

    // For registration of a new user, using certain inputs as credential
    @Override
    public UserData registerNewUser(RegisterDTO registerDTO){
        System.out.println("Inside service class --- register new user");
        UserData userData = null;
        userData = new UserData(registerDTO);

        // check if this user already exists in database
        userDataRepository.save(userData);

        // Generate otp and send it to the registered email
        int otp = otpGenerationUtil.generateOTP(registerDTO.emailId);
        emailSenderUtil.sendMail(registerDTO.emailId, "Registration OTP",
                                    "Your OTP = "+otp);
        System.out.println("Registered the new user: " + userData.toString());
        return userDataRepository.save(userData);
    }

    // For login of a user, using email and password as the credentials
    @Override
    public UserData loginUser(UserLoginDTO userLoginDTO){
        // Match email and password
        String email = userLoginDTO.emailId;
        String password = userLoginDTO.password;
        // load username and password here and do authentication
        // Once authentication is done we generate jwt token
        return userDataRepository.findByEmailIdAndPassword(email,password);
    }

    // Verify the user credentials using OTP sent to her email
    @Override
    public ResponseDTO verifyUserCredential(VerifyUser verifyUser) {
        UserData userData = null;

        userData = userDataRepository.findByEmailId(verifyUser.getEmailId());
        if(userData != null){
            // OTP stored in cache(LHS) should match with OTP passed in request body (RHS)

            if(otpGenerationUtil.getOTP(verifyUser.getEmailId()) == verifyUser.getOtp()){
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
            userDataRepository.delete(userData);
        }
        //System.out.println("The user does not exist");
    }

    // Get the details of a user with a given email id
    @Override
    public UserData getUserByEmailId(String emailId) {
        UserData userData = null;
        userData = userDataRepository.findByEmailId(emailId);
        return userData;
    }

    @Override
    public Optional<UserData> getUserById(Long userId) {
        Optional<UserData> userData = null;
        userData = userDataRepository.findById(Math.toIntExact(userId));
        return userData;
    }
    // Sends a token to the email id of the user, that token will be used to reset the password
    // so forgot password page has email id as input field, then it navigates to reset password field
    @Override
    public ResponseDTO forgotPassword(String emailId) {
        UserData userData = null;
        userData = userDataRepository.findByEmailId(emailId);
        if (userData == null){
            String msg = "The given emailId entered is NOT registered!";
            return new ResponseDTO(msg,null);
        }
        else if (userData != null){
            String token = jwtTokenUtil.generateToken(userData.getUserId());
            emailSenderUtil.sendMail("r_kjha@yahoo.com",
                                            "Your token to reset Password ", token);
            String msg = "Your token has been sent to your registered emailId "+ emailId
                        + ". Kindly use your token to reset your password."
                        + " It will expire in 10 Minutes!";
            return new ResponseDTO(msg,null);
        }
        return null;
    }

    // Reset the password after verification of the token and new password as input
    @SneakyThrows
    @Override
    public ResponseDTO resetPassword(String token, String pwdNew) {
        Long userId = jwtTokenUtil.decodeToken(token);
        if(userId == null){
            throw new InvalidTokenException("The given token is NOT valid!");
        }
        else {
            UserData userData = userDataRepository.findById(Math.toIntExact(userId)).get();

            if (userData == null){
                throw new UserNotFoundException("The given user is NOT registered!");
            }
            else if(userData != null){
                userData.setPassword(pwdNew);
                userDataRepository.save(userData);
                return new ResponseDTO("Password has been updated successfully for ",
                        userData.getFirstName()+ " "+ userData.getLastName() + "!");
            }
        }
        return null;
    }
}