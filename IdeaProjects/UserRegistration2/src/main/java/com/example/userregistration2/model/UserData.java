package com.example.userregistration2.model;

import com.example.userregistration2.DTO.RegisterDTO;
import com.example.userregistration2.DTO.UserDTO;
import lombok.NoArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_data")
@NoArgsConstructor
public @Data class UserData {

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="otp")
    private String otp;

    @Column(name="otp_requested_time")
    private Date otpRequestedTime;

    public boolean isOTPRequired() {
        if (this.getOtp() == null) {
            return false;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();

        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }
        return true;
    }

    public UserData(UserDTO userDTO){
        this.updateUserData(userDTO);
    }

    // constructor invoked when the user registers
    public UserData(RegisterDTO registerDTO){
        this.registerUserData(registerDTO);
    }

    public void registerUserData(RegisterDTO registerDTO) {
        this.name = registerDTO.name;
        this.password = registerDTO.password;
        this.email = registerDTO.email;
        this.address = registerDTO.address;
    }

    public void updateUserData(UserDTO userDTO) {
        this.name = userDTO.name;
        this.password = userDTO.password;
        this.email = userDTO.email;
        this.address = userDTO.address;
    }
}
