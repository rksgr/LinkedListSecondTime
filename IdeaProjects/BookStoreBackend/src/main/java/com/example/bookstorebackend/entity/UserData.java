package com.example.bookstorebackend.entity;

import com.example.bookstorebackend.dto.RegisterDTO;
import com.example.bookstorebackend.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.LocalDateType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "user_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public @Data
class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "kyc",unique=true)
    private String kyc;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "password")
    private String password;

    @Column(name = "email_id",unique=true)
    private String emailId;

    @Column(name = "verify")
    private Boolean verify;

    // 6-digit otp
    @Column(name = "otp")
    private Integer otp;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate = LocalDate.of(2020,10,10);

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    // constructor invoked when the user registers
    public UserData(UserDTO userDTO) {
        this.updateUserData(userDTO);
    }

    public UserData(RegisterDTO registerDTO) {
        this.registerUserData(registerDTO);
    }

    private void registerUserData(RegisterDTO registerDTO) {
        this.firstName = registerDTO.firstName;
        this.lastName = registerDTO.lastName;
        this.kyc = registerDTO.kyc;
        this.dob = registerDTO.dob;
        this.registeredDate = LocalDate.of(2022,3,11).plusDays(2);
        this.updatedDate = LocalDate.now();
        this.password = registerDTO.password;
        this.emailId = registerDTO.emailId;
        this.verify = true;
        this.purchaseDate = LocalDate.now();
        this.expiryDate = LocalDate.now();
    }

    public void updateUserData(UserDTO userDTO) {
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.kyc = userDTO.kyc;
        this.dob = userDTO.dob;
        this.registeredDate = userDTO.registeredDate;
        this.updatedDate = userDTO.updatedDate;
        this.password = userDTO.password;
        this.emailId = userDTO.emailId;
        this.verify = userDTO.verify;
        this.purchaseDate = userDTO.purchaseDate;
        this.expiryDate = userDTO.expiryDate;
    }
}