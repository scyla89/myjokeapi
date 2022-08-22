package com.example.myjokeapi.connector.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DadJoke {
    private String id;
    private String joke;
    private int status;

    @Override
    public String toString() {
        return String.format("ID=%s, Joke=%s", id, joke);
    }
}
