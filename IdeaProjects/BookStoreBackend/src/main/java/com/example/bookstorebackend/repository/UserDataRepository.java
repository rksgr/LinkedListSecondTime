package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByEmailId(String emailId);

    //@Query(value = "SELECT * from user_data where user_id= :userId",nativeQuery = true)
    //Optional<UserData> findById(Integer userId);

    Optional<UserData> findByEmailIdAndPassword(String emailId, String password);

    void delete(UserData userData);
}
