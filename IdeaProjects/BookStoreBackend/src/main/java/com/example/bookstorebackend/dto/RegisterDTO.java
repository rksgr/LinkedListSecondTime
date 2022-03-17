package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
public @ToString
class RegisterDTO {

    public String firstName;
    public String lastName;
    public String kyc;
    public LocalDate dob;
    //public LocalDate registeredDate;
    //public LocalDate updatedDate;
    public String password;
    public String emailId;
    //public Boolean verify;
    //public LocalDate purchaseDate;
    //public LocalDate expiryDate;
}
