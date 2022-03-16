package com.example.bookstorebackend.dto;

import lombok.Data;

@Data
public class VerifyOtp {

    private String email_id;
    private int otp;

    public VerifyOtp(String email_id, int OTP) {
        this.email_id = email_id;
        this.otp = OTP;
    }
}
