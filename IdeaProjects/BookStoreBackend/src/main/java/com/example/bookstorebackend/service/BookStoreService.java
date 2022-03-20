package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ChangeBookPriceDTO;
import com.example.bookstorebackend.dto.ChangeBookQtyDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.exception.BookStoreException;
import com.example.bookstorebackend.exception.InvalidTokenException;
import com.example.bookstorebackend.repository.BookRepository;
import com.example.bookstorebackend.util.EmailSenderUtil;
import com.example.bookstorebackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public EmailSenderUtil emailSenderUtil;

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
                .orElseThrow(() -> new BookStoreException("Book with book id" + bookId
                        + "does not exist!"));
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
    // Generate token and send to emailid in case of update quantity or price of book
    @Override
    public void updateBookTokenGen(Integer bookId){
        String token = jwtTokenUtil.generateIdBasedToken(bookId);
        emailSenderUtil.sendMail("r_kjha@yahoo.com",
                                "Token to update quantity or price of Books",
                                token);
        //return token;
    }

    // Change the quantity of given book in the store
    @Override
    public ResponseDTO changeBookQuantity(ChangeBookQtyDTO changeBookQtyDTO) throws InvalidTokenException {

        Integer bookId = jwtTokenUtil.decodeIdBasedToken(changeBookQtyDTO.getToken());

        System.out.println("Book Id  is=" + bookId);

        // check validity of token
        if ( bookId == null) {
            throw new InvalidTokenException("The given token is invalid!");
        }
        else if ( bookId != null) {

            Optional<BookData> bookDataOptional = bookRepository.findById(bookId);
            BookData bookData = bookDataOptional.get();
            // check if the book with given bookId exists in the bookstore
            if (bookData == null) {
                throw new BookStoreException("The book with book id " + bookId +
                        " not found!");
            } else if (bookData != null) {
                bookData.setBookQuantity(changeBookQtyDTO.bookQuantity);
                bookRepository.save(bookData);
                return new ResponseDTO("The Book quantity changed successfully!", bookData);
            }
        }
        return null;
    }

    // Change the price of given book in the store

    @Override
    public ResponseDTO changeBookPrice(ChangeBookPriceDTO changeBookPriceDTO) throws InvalidTokenException {

        // check validity of token
        Integer bookId = jwtTokenUtil.decodeIdBasedToken(changeBookPriceDTO.getToken());
        if ( bookId == null) {
            throw new InvalidTokenException("The given token is invalid!");
        } else if ( bookId != null) {

            // Check if the Book with given bookId exists in the bookstore
            Optional<BookData> bookDataOptional = bookRepository.findById(bookId);
            BookData bookData = bookDataOptional.get();
            if (bookData == null) {
                throw new BookStoreException("The book with Book Id " + changeBookPriceDTO.bookId
                        + " not found.");
            } else if (bookData != null) {

                bookData.setBookPrice(changeBookPriceDTO.getBookPrice());
                bookRepository.save(bookData);
                return new ResponseDTO("The Book price changed successfully!", bookData);
            }
        }
        return null;
    }
}

