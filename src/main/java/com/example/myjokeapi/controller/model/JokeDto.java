package com.example.myjokeapi.controller.model;

public class JokeDto {
    private String joke;

    public JokeDto(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return String.format("Joke=%s", joke);
    }

    public String getJoke() {
        return joke;
    }
}
