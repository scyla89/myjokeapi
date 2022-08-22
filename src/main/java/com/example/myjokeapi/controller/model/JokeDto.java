package com.example.myjokeapi.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JokeDto {
    String joke;
    String category;

    @Override
    public String toString() {
        return String.format("Joke=%s", joke);
    }
}
