package com.example.myjokeapi.connector;

import com.example.myjokeapi.exception.EmptyListException;
import com.example.myjokeapi.exception.JokeNotFoundException;
import com.example.myjokeapi.connector.model.DadJoke;
import com.example.myjokeapi.connector.model.DadJokeList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "DadJokesApi", url = "https://icanhazdadjoke.com")
public interface DadJokeApi {

    @GetMapping(value = "/", headers = "Accept=application/json")
    DadJoke getRandom();

    @GetMapping(value = "/j/{id}", headers = "Accept=application/json")
    DadJoke findById(@PathVariable String id) throws JokeNotFoundException;

    @GetMapping(value = "/search", headers = "Accept=application/json")
    DadJokeList findByTerm(@RequestParam String term, @RequestParam int limit) throws EmptyListException;
}
