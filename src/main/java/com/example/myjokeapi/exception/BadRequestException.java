package com.example.myjokeapi.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("The Request Body contains some errors.");
    }
}
