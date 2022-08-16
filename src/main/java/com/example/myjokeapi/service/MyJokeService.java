package com.example.myjokeapi.service;

import com.example.myjokeapi.connector.DadJokeService;
import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.service.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyJokeService {
    @Autowired
    DadJokeService dadJokesService;
    @Autowired
    MyJokeRepository myJokeRepository;

    /**
     * Dad Jokes
     */
    public JokeDto getRandom() {
        var dadJoke = dadJokesService.getRandom();
        Joke joke = new Joke(dadJoke);
        // Save the random joke in the DB
        myJokeRepository.save(joke);
        return dadJoke.toDto();
    }

    public JokeDto findById(String id) {
        var dadJoke = dadJokesService.findById(id);
        return dadJoke.toDto();
    }

    public List<JokeDto> findByTerm(String term) {
        var dadJokeList = dadJokesService.findByTerm(term);
        List<JokeDto> jokeDtoList = new ArrayList<>();
        dadJokeList.results.forEach ( dadJoke -> {
            jokeDtoList.add(dadJoke.toDto());
        });
        return jokeDtoList;
    }

    /**
     * DB queries
     */
    public List<Joke> findAll() {
        return myJokeRepository.findAll();
    }

    public Joke addJoke(Joke joke) {
        myJokeRepository.save(joke);
        return joke;
    }

    public List<Joke> findCategory(String category) {
        return myJokeRepository.findCategory(category);
    }
}
