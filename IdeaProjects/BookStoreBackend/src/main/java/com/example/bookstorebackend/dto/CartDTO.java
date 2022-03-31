package com.example.bookstorebackend.dto;

import lombok.ToString;


public @ToString
class CartDTO {

    public Long userId;

    public Long bookId;

    public Long quantity;

}
