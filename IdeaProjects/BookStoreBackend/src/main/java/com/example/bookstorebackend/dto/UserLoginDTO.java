package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
public @Data class UserLoginDTO {

    public String emailId;
    public String password;
}
