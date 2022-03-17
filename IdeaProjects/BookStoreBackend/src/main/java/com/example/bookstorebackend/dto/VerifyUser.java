package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data
class VerifyUser {

    private String emailId;
    private int otp;
}
