package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.CartDTO;
import com.example.bookstorebackend.entity.BookData;
import com.example.bookstorebackend.entity.CartData;
import com.example.bookstorebackend.entity.UserData;
import com.example.bookstorebackend.repository.CartRepository;
import com.example.bookstorebackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        //System.out.println("Inside cart service class --- add to Cart ");
        Long userId = jwtTokenUtil.decodeToken(cartDTO.userId);
        System.out.println("userId = " + userId);
        Optional<UserData> userData = userService.getUserById(userId);

        if  (userData.isPresent()){
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
    /*
    public List<CartData> findAllCarts(String token){
        Long userId = jwtTokenUtil.decodeToken(token);
        Optional<UserData> userData = userService.getUserById(userId);
        if (userData.isPresent()){
            System.out.println("Inside find all carts --  inside if block");
            List<CartData> cartList = cartRepository.findAllCartsByUserId(userId);
            return cartList;
         }
        return null;
    } */

    @Override
    public List<BookData> getAllCartItemsForUser(String token) {
        Long userId = jwtTokenUtil.decodeToken(token);
        List<BookData> bookDataList ;
        Optional<UserData> userData = userService.getUserById(userId);
        if (userData.isPresent()){

            List<CartData> cartList = cartRepository.findAllCartsByUserId(userId);
            System.out.println("Inside find all carts --  inside if block -- printing list of all " +
                                "carts for the particular userid");
            cartList.stream().forEach(cartData -> System.out.println(cartData.getCartId()));
            System.out.println();
            bookDataList = cartList.stream()
                                    //.map(id -> id.getUserData())
                                    .filter(cartData -> cartData.getUserData().getUserId() == userId)
                                    .map(cartData -> cartData.getBookData())
                                    .collect(Collectors.toList());
            return bookDataList;
        }
        return null;
    }

    @Override
    public Object updateItemQuantityCart() {
        System.out.println("Inside cart service class --- update items in Cart working.");
        return null;
    }
}
