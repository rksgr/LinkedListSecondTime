package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.entity.BookStore;
import org.springframework.http.RequestEntity;

import java.util.List;

public interface IBookStoreService {

    List<BookStore> getAllBooks();

    BookStore getBookById(int bookId);

    void deleteBookById(int bookId);

    BookStore addBookToStore(BookStoreDTO bookStoreDTO);

    BookStore changeBookQuantity(int bookId, int bookQuantity);
}
