package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.CartDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.entity.CartData;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICartService {
    Object getAllCartItems();

    CartData addToCart(CartDTO cartDTO);

    Object removeFromCart();

    List<BookData> getAllCartItemsForUser(String token);

    Object updateItemQuantityCart();

    //List<CartData> findAllCarts(String token);
}
