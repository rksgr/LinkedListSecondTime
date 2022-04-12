package com.example.userregistration2.service;

import com.example.userregistration2.DTO.RegisterDTO;
import com.example.userregistration2.model.JwtRequest;
import com.example.userregistration2.model.JwtResponse;
import com.example.userregistration2.model.UserData;
import com.example.userregistration2.repository.UserDataRepository;
import com.example.userregistration2.util.EmailSenderService;
import com.example.userregistration2.util.OtpValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.bytebuddy.utility.RandomString;

import javax.mail.MessagingException;

@Service
public class CustomUserDataService implements IUserDataService, UserDetailsService {

    @Autowired
    public UserDataRepository userDataRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private OtpValidation otpValidation;
    //@Autowired
    //private PasswordEncoder passwordEncoder;


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
    }

    // Returns userdata based on the email of the user
    public UserData getUserByEmail(String email){
        return userDataRepository.findByEmail(email);
    }

    @Override
    public List<UserData> getAllUserData() {
        System.out.println("Inside service class --- get all user data");
        return userDataRepository.findAll();
    }

    // For registration of a user, using certain inputs as credential
    @Override
    public UserData registerNewUser(RegisterDTO registerDTO){
        UserData userData = null;
        userData = new UserData(registerDTO);
        System.out.println("Registered the new user: " + userData.toString());
        try{
            generateOneTimePassword(userData);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userDataRepository.save(userData);
    }


    // For login of a user, using email and password as the credentials

    @Override
    public UserData loginUser(JwtRequest jwtRequest){
        //System.out.println("Inside service class --- login new user");
        // Match email and password
        String email = jwtRequest.email;
        String password = jwtRequest.password;
        //if (jwtResponse.getToken().equals())
        System.out.println("username(email): "+ email+" password: "+ password);
        // load username and password here and do authentication
        // Once authentication is done we generate jwt token
        return userDataRepository.findByEmailAndPassword(email,password);
    }

    public void generateOneTimePassword(UserData userData)
            throws UnsupportedEncodingException, MessagingException {
        String otp = RandomString.make(6);
        System.out.println("otp generated is: "+ otp);

        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String encodedOtp = passwordEncoder.encode(otp);
        //userData.setOtp(encodedOtp);
        userData.setOtp(otp);
        userData.setOtpRequestedTime(new Date());
        // userDataRepository.save(userData);
        sendOTPEmail(userData, otp);
        if(otpValidation.validateOtp(otp)){
            userDataRepository.save(userData);
        }else{
            System.out.println("otp entered is not correct!");
        }
    }

    // Method to send an email containing OTP to the user
    private void sendOTPEmail(UserData userData, String otp)
            throws UnsupportedEncodingException, MessagingException{
        //MimeMessage message = mailSender.createMimeMessage();
        //MimeMessageHelper helper = new MimeMessageHelper(message);
        String content = "Your otp for registration is "+ otp +
                "\nUse this password for first time registration\n"+
                "It is set to expire in 5 minutes";
        emailSenderService.sendSimpleMail(userData.getEmail(),
                content,"Your One time password");
        //helper.setFrom("rahul34jha@gmail.com", "One Time password");

        //helper.setTo("r_kjha@yahoo.com");
        //helper.setText(content, true);
        //mailSender.send(message);
    }
    // clear OTP request status upon successful registration of the user
    public void clearOTP(UserData userData){
        userData.setOtp(null);
        userData.setOtpRequestedTime(null);
        userDataRepository.save(userData);
    }


}