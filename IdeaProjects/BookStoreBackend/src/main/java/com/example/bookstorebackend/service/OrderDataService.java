package com.example.bookstorebackend.service;

import com.example.bookstorebackend.entity.OrderData;
import com.example.bookstorebackend.repository.OrderDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDataService implements IOrderDataService{

    @Autowired
    private OrderDataRepository orderDataRepository;

    List<OrderData> orderDataList = new ArrayList<>();

    @Override
    public List<OrderData> getAllOrderData() {
        System.out.println("Inside OrderDataService Method");
        return orderDataRepository.findAll();
    }


}
