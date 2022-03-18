package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ChangeBookPriceDTO;
import com.example.bookstorebackend.dto.ChangeBookQtyDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.exception.BookStoreException;
import com.example.bookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 */

@Service
public class BookStoreService implements IBookStoreService {

    @Autowired
    private BookRepository bookRepository;

    // Method to return all the books
    @Override
    public List<BookData> getAllBooks() {
        return bookRepository.findAll();
    }

    // Method to fetch book based on bookId
    @Override
    public BookData getBookById(int bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(()->new BookStoreException("Book with book id"+ bookId
                        +"does not exist!"));
    }

    // Method to delete book based on id of the book
    @Override
    public void deleteBookById(int bookId) {
        bookRepository.deleteById(bookId);
    }

    // Method to add new book to the bookstore
    @Override
    public BookData addBookToStore(BookStoreDTO bookStoreDTO) {
        BookData bookStore = new BookData(bookStoreDTO);
        return bookRepository.save(bookStore);
    }

    // Change the quantity of given book in the store
    @Override
    public BookData changeBookQuantity(ChangeBookQtyDTO changeBookQtyDTO) {
        BookData bookData = getBookById(changeBookQtyDTO.bookId);
        bookData.setBookQuantity(changeBookQtyDTO.bookQuantity);
        bookRepository.save(bookData);
        return bookData;
    }

    // Change the quantity of given book in the store
    @Override
    public BookData changeBookPrice(ChangeBookPriceDTO changeBookPriceDTO) {
        BookData bookData = getBookById(changeBookPriceDTO.bookId);
        bookData.setBookPrice(changeBookPriceDTO.bookPrice);
        bookRepository.save(bookData);
        return bookData;
    }
}

