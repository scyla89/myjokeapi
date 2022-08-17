package com.example.myjokeapi.connector.model;

import com.example.myjokeapi.controller.model.JokeDto;

public record DadJoke(
        String id,
        String joke,
        int status)
{
    // Entity to DTO converter
    public JokeDto toDto() {
        return new JokeDto(joke);
    }

    @Override
    public String toString() {
        return String.format("ID=%s, Joke=%s", id, joke);
    }
}
