package com.example.bookstorebackend.dto;

import lombok.ToString;


public @ToString
class CartDTO {

    public String userId;

    public Long bookId;

    public Long quantity;

}
