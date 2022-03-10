package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;


public @ToString class BookStoreDTO {

    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookLogo;
    public double bookPrice;
    public int bookQuantity;
}
