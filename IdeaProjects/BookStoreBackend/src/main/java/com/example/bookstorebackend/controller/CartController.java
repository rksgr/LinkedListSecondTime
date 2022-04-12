package com.example.bookstorebackend.controller;

import com.example.bookstorebackend.dto.CartDTO;
import com.example.bookstorebackend.dto.ResponseDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.entity.CartData;
import com.example.bookstorebackend.service.IBookStoreService;
import com.example.bookstorebackend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private ICartService cartService;

    /**
     * Method to get the list of all the books present in the cart
     * @return
     */
    @RequestMapping("/get")
    public ResponseEntity<ResponseDTO> getAllCartItems(){
        System.out.println("Inside cart controller");
        ResponseDTO respDto = new ResponseDTO("Get All Cart items Method working"
                ,cartService.getAllCartItems());
        ResponseEntity responseEntity = new ResponseEntity(respDto, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to get all the books present in the cart for the user
     * @return List of all the books in the cart
     */
    @RequestMapping("/getBookList/{token}")
    public ResponseEntity<ResponseDTO> getAllBooksInCartsForUser(@PathVariable("token") String token){
        //System.out.println("Inside cart controller -- getAllBooksInCartsForUser method. ");
        List<BookData> bookDataList = new ArrayList<>();
        bookDataList = cartService.getAllCartItemsForUser(token);
        //System.out.println("cartlist size: "+cartList.size());


        System.out.println("bookList size: "+ bookDataList.size());

        ResponseDTO respDto = new ResponseDTO("Get All Books in cart Method working"
                ,bookDataList);
        ResponseEntity responseEntity = new ResponseEntity(respDto,HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to add a book to the cart
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO){
        System.out.println("Inside cart controller -- add to cart");
        ResponseDTO respDto = new ResponseDTO("Get All Cart items Method working"
                ,cartService.addToCart(cartDTO));
        System.out.println("Iside cart controlelr: userid is "+ cartDTO.userId);
        ResponseEntity responseEntity = new ResponseEntity(respDto, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to remove a book from the cart
     * @return
     */
    @RequestMapping("/remove")
    public ResponseEntity<ResponseDTO> removeFromCart(){
        System.out.println("Inside cart controller -- remove from cart");
        ResponseDTO respDto = new ResponseDTO("Remove book from Cart Method working"
                ,cartService.removeFromCart());
        ResponseEntity responseEntity = new ResponseEntity(respDto, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Method to update quantity of items in the cart
     * @return
     */
    @RequestMapping("/update")
    public ResponseEntity<ResponseDTO> updateItemQuantityCart(){
        System.out.println("Inside cart controller -- update item quantity cart");
        ResponseDTO respDto = new ResponseDTO("update item quantity in Cart Method working"
                ,cartService.updateItemQuantityCart());
        ResponseEntity responseEntity = new ResponseEntity(respDto, HttpStatus.OK);
        return responseEntity;
    }
}
