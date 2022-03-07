package com.example.userregistration2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
public @ToString class RegisterDTO {
    public String name;
    public String email;
    public String address;
    public String password;
}
