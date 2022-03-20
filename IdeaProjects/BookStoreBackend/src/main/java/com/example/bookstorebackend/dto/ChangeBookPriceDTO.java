package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
public @ToString
class ChangeBookPriceDTO {

    private String token;
    public int bookId;
    public Double bookPrice;
}
