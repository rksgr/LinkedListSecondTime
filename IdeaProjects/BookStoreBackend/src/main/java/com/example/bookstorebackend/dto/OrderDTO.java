package com.example.bookstorebackend.dto;

import lombok.ToString;

import java.time.LocalDate;

public @ToString
class OrderDTO {

    public LocalDate orderDate;
    public int price;
    public int quantity;
    public String address;
    public Long userId;
    public Long bookId;
    public boolean cancel;

}
