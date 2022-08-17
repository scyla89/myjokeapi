package com.example.myjokeapi.connector;

import com.example.myjokeapi.exception.EmptyListException;
import com.example.myjokeapi.exception.JokeNotFoundException;
import com.example.myjokeapi.connector.model.DadJoke;
import com.example.myjokeapi.connector.model.DadJokeList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DadJokeService {
    private final DadJokeApi dadJokeApi;

    public DadJoke getRandom() {
        DadJoke dadJoke = dadJokeApi.getRandom();
        return dadJoke;
    }

    public DadJoke findById(String id) {
        DadJoke dadJoke = dadJokeApi.findById(id);
        if (dadJoke.status() == 200) {
            return dadJoke;
        } else if (dadJoke.status() == 404){
            throw new JokeNotFoundException(id);
        }
        return dadJoke;
    }

    public DadJokeList findByTerm(String term) {
        DadJokeList dadJokeList = dadJokeApi.findByTerm(term, 3);
        if (dadJokeList.results().isEmpty()) {
            throw new EmptyListException(term);
        }
        return dadJokeList;
    }
}
