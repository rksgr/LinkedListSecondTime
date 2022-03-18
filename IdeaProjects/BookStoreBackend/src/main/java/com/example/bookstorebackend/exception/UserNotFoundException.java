package com.example.bookstorebackend.exception;

public class UserNotFoundException extends Throwable {

    public UserNotFoundException(String message) {
        super(message);
    }
}
