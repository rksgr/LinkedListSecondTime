package com.example.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
public @ToString
class ChangeBookQtyDTO {

    // public String token;
    public int bookId;
    public int bookQuantity;

}
