package com.example.userregistration2.repository;

import com.example.userregistration2.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    //@Query(value="select * from user_data;", nativeQuery=true)
    //List<UserData> findAll();
    //List<UserData> find

    /**
    // Query for login of the user
    @Query(value="select * from user_data where email=:email and password=:password",
            nativeQuery = true)
    UserData findBy(@Param("email") String email, @Param("password") String password);
    */
    UserData findByEmail(String email);
    UserData findByEmailAndPassword(String email, String password);
}
