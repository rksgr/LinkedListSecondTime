package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ChangeBookPriceDTO;
import com.example.bookstorebackend.dto.ChangeBookQtyDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.exception.InvalidTokenException;

import java.util.List;

public interface IBookStoreService {

    List<BookData> getAllBooks();

    BookData getBookById(int bookId);

    void deleteBookById(int bookId);

    BookData addBookToStore(BookStoreDTO bookStoreDTO);

    void updateBookTokenGen(Integer bookId);

    ResponseDTO changeBookQuantity(ChangeBookQtyDTO changeBookQtyDTO) throws InvalidTokenException;

    // Change the quantity of given book in the store
    ResponseDTO changeBookPrice(ChangeBookPriceDTO changeBookPriceDTO) throws InvalidTokenException;
}
