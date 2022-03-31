package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.CartDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.entity.CartData;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.repository.CartRepository;
import com.example.bookstorebackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomUserDataService userService;

    @Autowired
    BookStoreService bookStoreService;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Object getAllCartItems() {
        System.out.println("Inside cart service class ");
        return null;
    }

    @Override
    public CartData addToCart(CartDTO cartDTO) {
        System.out.println("Inside cart service class --- add to Cart ");
        Long userId = jwtTokenUtil.decodeToken(String.valueOf(cartDTO.userId));
        Optional<UserData> userData = userService.getUserById(userId);
        if(userData.isPresent()){
            BookData bookData = bookStoreService.getBookById(cartDTO.bookId);
            CartData cartData =new CartData(userData.get(),bookData,cartDTO.quantity);
            return cartRepository.save(cartData);
        }
        return null;
    }

    @Override
    public Object removeFromCart() {
        System.out.println("Inside cart service class --- remove from Cart ");
        return null;
    }

    @Override
    public Object getAllCartItemsForUser() {
        System.out.println("Inside cart service class --- get all items from Cart for user");
        return null;
    }

    @Override
    public Object updateItemQuantityCart() {
        System.out.println("Inside cart service class --- update items in Cart working.");
        return null;
    }
}
