package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {


    UserData findByEmailId(String emailId);

    UserData findByEmailIdAndPassword(String emailId, String password);
}
