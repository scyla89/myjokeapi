package com.example.myjokeapi.connector.model;

import com.example.myjokeapi.controller.model.JokeDto;

public class DadJoke {
    private String id;
    private String joke;
    private int status;

    // Entity to DTO converter
    public JokeDto toDto() {
        return new JokeDto(
            this.getJoke()
        );
    }

    @Override
    public String toString() {
        return String.format("ID=%s, Joke=%s", id, joke);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
