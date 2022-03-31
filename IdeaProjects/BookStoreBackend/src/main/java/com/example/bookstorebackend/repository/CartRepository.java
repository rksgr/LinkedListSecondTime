package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartData, Integer> {

}
