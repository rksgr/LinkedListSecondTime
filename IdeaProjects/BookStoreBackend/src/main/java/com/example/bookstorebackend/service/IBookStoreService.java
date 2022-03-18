package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ChangeBookPriceDTO;
import com.example.bookstorebackend.dto.ChangeBookQtyDTO;
import com.example.bookstorebackend.entity.BookData;

import java.util.List;

public interface IBookStoreService {

    List<BookData> getAllBooks();

    BookData getBookById(int bookId);

    void deleteBookById(int bookId);

    BookData addBookToStore(BookStoreDTO bookStoreDTO);

    BookData changeBookQuantity(ChangeBookQtyDTO changeBookQtyDTO);

    // Change the quantity of given book in the store
    BookData changeBookPrice(ChangeBookPriceDTO changeBookPriceDTO);
}
