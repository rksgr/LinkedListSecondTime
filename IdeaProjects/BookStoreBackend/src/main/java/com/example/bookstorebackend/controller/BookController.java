package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.BookStore;
import com.example.bookstorebackend.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookstore")
public class BookController {

    @Autowired
    private IBookStoreService bookStoreService;

    /**
     * Method to get the list of all the books present in the store
     * @return
     */
    @RequestMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        ResponseDTO respDto = new ResponseDTO("Get All Books Method working"
                                                ,bookStoreService.getAllBooks());
        ResponseEntity responseEntity = new ResponseEntity(respDto,HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to get the book of given id from the store
     * @param bookId
     * @return
     */
    @RequestMapping("/getById/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") int bookId){
        ResponseDTO respDTO = new ResponseDTO("Get Book by BookId working "
                                                ,bookStoreService.getBookById(bookId));
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to add a new book to the book store
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBookToStore(@RequestBody BookStoreDTO bookStoreDTO){
        BookStore bookStore = null;
        bookStore = bookStoreService.addBookToStore(bookStoreDTO);
        ResponseDTO respDTO = new ResponseDTO("Added Book to the BookStore working ", bookStore);
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to change the number of books in the book store
     */
    @PutMapping("/update/{bookId}/{qty}")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@PathVariable("bookId") int bookId,
                                                          @PathVariable("qty") int bookQuantity){
        BookStore bookStore = null;
        bookStore = bookStoreService.changeBookQuantity(bookId, bookQuantity);
        ResponseDTO respDTO = new ResponseDTO("Change number of Books in the BookStore working ", bookStore);
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }
    /**
     * Method to delete a book from the bookstore
     * @param bookId
     * @return void
     */
    @RequestMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable("bookId") int bookId){
        bookStoreService.deleteBookById(bookId);
        ResponseDTO respDTO = new ResponseDTO("Delete Book by BookId working ", "deleted");
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }
}
