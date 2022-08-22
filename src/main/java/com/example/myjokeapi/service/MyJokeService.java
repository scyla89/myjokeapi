package com.example.myjokeapi.service;

import com.example.myjokeapi.connector.DadJokeService;
import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.database.repository.MyJokeRepository;
import com.example.myjokeapi.database.model.JokeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyJokeService {
    private final DadJokeService dadJokeService;
    private final MyJokeRepository myJokeRepository;

    /**
     * Dad Jokes
     */
    public JokeDto getRandom() {
        var dadJoke = dadJokeService.getRandom();
        // Save the random joke in the DB
        JokeEntity jokeEntity = new JokeEntity(dadJoke);
        myJokeRepository.save(jokeEntity);
        return dadJoke.toDto();
    }

    public JokeDto findById(String id) {
        var dadJoke = dadJokeService.findById(id);
        return dadJoke.toDto();
    }

    public List<JokeDto> findByTerm(String term) {
        var dadJokeList = dadJokeService.findByTerm(term);
        List<JokeDto> jokeDtoList = new ArrayList<>();
        dadJokeList.results().forEach ( dadJoke -> {
            jokeDtoList.add(dadJoke.toDto());
        });
        return jokeDtoList;
    }

    /**
     * DB queries
     */
    public List<JokeDto> findAll() {
        var jokeEntityList = myJokeRepository.findAll();
        List<JokeDto> jokeDtoList = new ArrayList<>();
        jokeEntityList.forEach ( jokeEntity -> {
            jokeDtoList.add(jokeEntity.toDto());
        });
        return jokeDtoList;
    }

    public JokeEntity addJoke(JokeEntity jokeEntity) {
        myJokeRepository.save(jokeEntity);
        return jokeEntity;
    }

    public List<JokeDto> findCategory(String category) {
        var jokeEntityList = myJokeRepository.findCategory(category);
        List<JokeDto> jokeDtoList = new ArrayList<>();
        jokeEntityList.forEach ( joke -> {
            jokeDtoList.add(joke.toDto());
        });
        return jokeDtoList;
    }

}
