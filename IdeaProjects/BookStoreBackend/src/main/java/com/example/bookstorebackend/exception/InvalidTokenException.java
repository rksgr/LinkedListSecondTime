package com.example.bookstorebackend.exception;

public class InvalidTokenException extends Throwable {
    public InvalidTokenException(String message) {
        super(message);
    }
}
