package com.example.bookstorebackend.entity;

import com.example.bookstorebackend.dto.BookStoreDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name="bookstock")
public @Data class BookData {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    @Column(name="book_name")
    private String bookName;

    @Column(name="book_author")
    private String bookAuthor;

    @Column(name="book_description")
    private String bookDescription;

    @Column(name="book_logo")
    private String bookLogo;

    @Column(name="book_price")
    private double bookPrice;

    @Column(name="book_quantity")
    private int bookQuantity;

    public BookData(BookStoreDTO bookStoreDTO){
        this.updateBookStoreData(bookStoreDTO);
    }

    public void updateBookStoreData(BookStoreDTO bookStoreDTO){
        this.bookName = bookStoreDTO.bookName;
        this.bookAuthor = bookStoreDTO.bookAuthor;
        this.bookDescription = bookStoreDTO.bookDescription;
        this.bookLogo = bookStoreDTO.bookLogo;
        this.bookPrice = bookStoreDTO.bookPrice;
        this.bookQuantity = bookStoreDTO.bookQuantity;
    }
}
