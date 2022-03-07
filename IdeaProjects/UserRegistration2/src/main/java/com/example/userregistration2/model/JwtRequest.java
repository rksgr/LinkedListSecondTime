package com.example.userregistration2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class JwtRequest {

    public String email;
    public String password;
}
