package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.BookStoreDTO;
import com.example.bookstorebackend.dto.ChangeBookPriceDTO;
import com.example.bookstorebackend.dto.ChangeBookQtyDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.exception.InvalidTokenException;
import com.example.bookstorebackend.service.IBookStoreService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookstore")
@CrossOrigin
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
     * Method to generate token to update the price or quantity of a given Book in the book store
     */
    @RequestMapping("/bookUpdateToken")
    public ResponseEntity<ResponseDTO> updateBookTokenGen(@RequestParam("bookId") Integer bookId){
        ResponseDTO respDTO = null;
        System.out.println("Inside controller update book token gen ");
        bookStoreService.updateBookTokenGen(bookId);
        respDTO = new ResponseDTO("Token generation to update the price or quantity of a given Book in the book store ", null);
        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }


    /**
     * Method to change the quantity of a given Book in the book store
     */
    @PutMapping("/updateQty")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestBody ChangeBookQtyDTO changeBookQtyDTO){
        ResponseDTO respDTO = null;
        //System.out.println("Inside controller update ");
        try {
            respDTO = bookStoreService.changeBookQuantity(changeBookQtyDTO);
        } catch (InvalidTokenException e) {
            e.printStackTrace();
        }

        ResponseEntity responseEntity = new ResponseEntity(respDTO, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to change the price of a given book in the book store
     */
    @SneakyThrows
    @PutMapping("/updatePrice")
    public ResponseEntity<ResponseDTO> changeBookPrice(@RequestBody ChangeBookPriceDTO changeBookPriceDTO){
        ResponseDTO respDTO = null;
        System.out.println("Inside controller update ");
        respDTO = bookStoreService.changeBookPrice(changeBookPriceDTO);

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
