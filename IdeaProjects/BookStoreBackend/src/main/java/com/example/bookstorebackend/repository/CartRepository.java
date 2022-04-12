package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<CartData, Long> {

    @Query(value="select * from cart_data where user_id=:user_id", nativeQuery = true)
    List<CartData> findAllCartsByUserId(Long user_id);

    //@Query(value = "SELECT * FROM cart_data where user_id= :user_id", nativeQuery = true)
    //List<CartData> findAllCartsByUserId(Long userId);
}
