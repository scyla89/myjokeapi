package com.example.myjokeapi.connector;

import com.example.myjokeapi.config.EmptyListException;
import com.example.myjokeapi.config.JokeNotFoundException;
import com.example.myjokeapi.connector.model.DadJoke;
import com.example.myjokeapi.connector.model.DadJokeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadJokeService {
    @Autowired
    private DadJokeApi dadJokesApi;

    public DadJoke getRandom() {
        DadJoke dadJoke = dadJokesApi.getRandom();
        return dadJoke;
    }

    public DadJoke findById(String id) {
        DadJoke dadJoke = dadJokesApi.findById(id);
        if (dadJoke.getStatus() == 200) {
            return dadJoke;
        } else if (dadJoke.getStatus() == 404){
            throw new JokeNotFoundException(id);
        }
        return dadJoke;
    }

    public DadJokeList findByTerm(String term) {
        DadJokeList dadJokeList = dadJokesApi.findByTerm(term, 3);
        if (dadJokeList.results.isEmpty()) {
            throw new EmptyListException(term);
        }
        return dadJokeList;
    }
}
