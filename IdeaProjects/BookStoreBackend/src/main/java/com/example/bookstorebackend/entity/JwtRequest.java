package com.example.bookstorebackend.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
public @Data class JwtRequest {
    public String emailId;
    public String password;
}
