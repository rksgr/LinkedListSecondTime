package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.CartDTO;
import com.example.bookstorebackend.entity.CartData;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICartService {
    Object getAllCartItems();

    CartData addToCart(CartDTO cartDTO);

    Object removeFromCart();

    Object getAllCartItemsForUser();

    Object updateItemQuantityCart();
}
