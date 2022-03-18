package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ChangeBookPriceDTO;
import com.example.bookstorebackend.dto.ChangeBookQtyDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        BookData bookStore = null;
        bookStore = bookStoreService.addBookToStore(bookStoreDTO);
        ResponseDTO respDTO = new ResponseDTO("Added Book to the BookStore working ", bookStore);
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to change the quantity of a given Book in the book store
     */
    @PutMapping("/updateQty")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestBody ChangeBookQtyDTO changeBookQtyDTO){
        BookData bookStore = null;
        //System.out.println("Inside controller update ");
        bookStore = bookStoreService.changeBookQuantity(changeBookQtyDTO);
        ResponseDTO respDTO = new ResponseDTO("Change quantity of a given Book in the BookStore working ", bookStore);
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to change the price of a given book in the book store
     */
    @PutMapping("/updatePrice")
    public ResponseEntity<ResponseDTO> changeBookPrice(@RequestBody ChangeBookPriceDTO changeBookPriceDTO){
        BookData bookData = null;
        System.out.println("Inside controller update ");
        bookData = bookStoreService.changeBookPrice(changeBookPriceDTO);
        ResponseDTO respDTO = new ResponseDTO("Change price of a Book in the BookStore working ", bookData);
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
