package com.example.bookstorebackend.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class UserDTO {

    public String firstName;
    public String lastName;
    public String kyc;
    public LocalDate dob;
    public LocalDate registeredDate;
    public LocalDate updatedDate;
    public String password;
    public String emailId;
    public Boolean verify;
    public LocalDate purchaseDate;
    public LocalDate expiryDate;
}
