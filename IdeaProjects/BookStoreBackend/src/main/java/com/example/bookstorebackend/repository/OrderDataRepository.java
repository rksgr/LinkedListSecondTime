package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDataRepository extends JpaRepository<OrderData, Integer> {

    //List<OrderData>

}
