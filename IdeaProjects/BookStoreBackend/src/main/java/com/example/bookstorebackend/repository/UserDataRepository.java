package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {

    UserData findByEmailId(String emailId);

    UserData findByEmailIdAndPassword(String emailId, String password);

    void delete(UserData userData);
}
