package com.example.myjokeapi.service;

import com.example.myjokeapi.connector.DadJokeService;
import com.example.myjokeapi.connector.model.DadJoke;
import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.database.repository.MyJokeRepository;
import com.example.myjokeapi.database.model.JokeEntity;
import lombok.RequiredArgsConstructor;
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
        myJokeRepository.save(mapToJokeEntity(dadJoke));
        return mapToJokeDto(dadJoke);
    }

    public JokeDto findById(String id) {
        var dadJoke = dadJokeService.findById(id);
        return mapToJokeDto(dadJoke);
    }

    public List<JokeDto> findByTerm(String term) {
        var dadJokeList = dadJokeService.findByTerm(term);
        List<JokeDto> jokeDtoList = new ArrayList<>();
        dadJokeList.results().forEach ( dadJoke -> {
            jokeDtoList.add(mapToJokeDto(dadJoke));
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
            jokeDtoList.add(mapToJokeDto(jokeEntity));
        });
        return jokeDtoList;
    }

    public JokeDto addJoke(JokeDto jokeDto) {
        myJokeRepository.save(mapToJokeEntity(jokeDto));
        return jokeDto;
    }

    public List<JokeDto> findCategory(String category) {
        var jokeEntityList = myJokeRepository.findCategory(category);
        List<JokeDto> jokeDtoList = new ArrayList<>();
        jokeEntityList.forEach ( jokeEntity -> {
            jokeDtoList.add(mapToJokeDto(jokeEntity));
        });
        return jokeDtoList;
    }

    /**
     * Mappers
     */
    private JokeDto mapToJokeDto(DadJoke dadJoke) {
        JokeDto jokeDto = new JokeDto();
        jokeDto.setJoke(dadJoke.getJoke());
        jokeDto.setCategory("dadjoke");
        return jokeDto;
    }

    private JokeDto mapToJokeDto(JokeEntity jokeEntity) {
        JokeDto jokeDto = new JokeDto();
        jokeDto.setJoke(jokeEntity.getJoke());
        jokeDto.setCategory(jokeEntity.getCategory());
        return jokeDto;
    }

    private JokeEntity mapToJokeEntity(DadJoke dadJoke) {
        JokeEntity joke = new JokeEntity();
        joke.setExternalid(dadJoke.getId());
        joke.setJoke(dadJoke.getJoke());
        joke.setCategory("dadjoke");
        return joke;
    }

    private JokeEntity mapToJokeEntity(JokeDto jokeDto) {
        JokeEntity jokeEntity = new JokeEntity();
        jokeEntity.setExternalid("none");
        jokeEntity.setJoke(jokeDto.getJoke());
        jokeEntity.setCategory(jokeDto.getCategory());
        return jokeEntity;
    }
}
