package com.example.bookstorebackend.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
public @Data class JwtResponse {

    public String token;
}
