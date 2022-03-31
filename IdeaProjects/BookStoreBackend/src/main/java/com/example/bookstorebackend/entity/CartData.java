package com.example.bookstorebackend.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="cart_data")
public @Data class CartData{

    @Id
    @Column(name="cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserData userData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private BookData bookData;

    private Long quantity;

    public CartData()   {}

    public CartData(UserData userData, BookData bookData, Long quantity){
        this.userData = userData;
        this.bookData = bookData;
        this.quantity = quantity;
    }
}