package com.example.bookstorebackend.entity;

import com.example.bookstorebackend.dto.CartDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="BookId")
public @Data
class BookId {

    @Id
    @Column(name="book_id")
    private Long bookId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="user_id")
    CartData cartData;

    public BookId() {}

    public BookId(Long bookId, int quantity, CartData cartData){
        this.bookId = bookId;
        this.quantity = quantity;
        this.cartData = cartData;
    }
    public BookId(CartDTO cartDTO){
        fillAllEntries(cartDTO);
    }

    private void fillAllEntries(CartDTO cartDTO) {
    }
}
