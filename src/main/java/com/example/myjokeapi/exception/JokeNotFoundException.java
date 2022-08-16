package com.example.myjokeapi.exception;

public class JokeNotFoundException extends RuntimeException {

    public JokeNotFoundException(String id) {
        super("Joke not found with ID " + id);
    }
}
