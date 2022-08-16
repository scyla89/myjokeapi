package com.example.myjokeapi.config;

public class JokeNotFoundException extends RuntimeException {

    public JokeNotFoundException(String id) {
        super("Joke not found with ID " + id);
    }
}
