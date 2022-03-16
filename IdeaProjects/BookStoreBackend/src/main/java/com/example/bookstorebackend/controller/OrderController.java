package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.OrderData;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.service.IOrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderdataservice")
public class OrderController {

    @Autowired
    private IOrderDataService orderDataService;

    @RequestMapping(value = {"/getAllOrders"})
    public ResponseEntity<ResponseDTO> getAllOrders()
    {
        List<OrderData> orderDataList = null;
        System.out.println("Inside Order Controller Class");

        orderDataList = orderDataService.getAllOrderData();
        ResponseDTO responseDTO = new ResponseDTO("Get all Order details call success", orderDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
