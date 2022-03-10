package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.entity.BookStore;
import com.example.bookstorebackend.entity.JwtRequest;
import com.example.bookstorebackend.exception.BookStoreException;
import com.example.bookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookStoreService implements IBookStoreService {

    @Autowired
    private BookRepository bookRepository;

    // Method to return all the books
    @Override
    public List<BookStore> getAllBooks() {
        return bookRepository.findAll();
    }

    // Method to fetch book based on bookId
    @Override
    public BookStore getBookById( int bookId) {
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
    public BookStore addBookToStore(BookStoreDTO bookStoreDTO) {
        BookStore bookStore = new BookStore(bookStoreDTO);
        return bookRepository.save(bookStore);
    }

}
