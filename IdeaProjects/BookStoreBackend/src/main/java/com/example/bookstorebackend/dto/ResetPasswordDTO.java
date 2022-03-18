package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data
class ResetPasswordDTO {

    private String jwtToken;
    private String newPasswrd;
}
