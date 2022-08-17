package com.example.myjokeapi.controller.model;

public record JokeDto(
        String joke)
{
    @Override
    public String toString() {
        return String.format("Joke=%s", joke);
    }
}
