package com.example.myjokeapi.config;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("The Request Body contains some errors.");
    }
}
