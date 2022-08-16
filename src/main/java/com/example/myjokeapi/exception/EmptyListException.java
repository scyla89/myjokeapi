package com.example.myjokeapi.exception;

public class EmptyListException extends RuntimeException {

    public EmptyListException(String term) {
        super("There are no jokes containing the term '" + term + "'.");
    }

}
