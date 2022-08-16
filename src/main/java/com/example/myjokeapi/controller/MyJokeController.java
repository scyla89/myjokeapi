package com.example.myjokeapi.controller;

import com.example.myjokeapi.config.BadRequestException;
import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.service.MyJokeService;
import com.example.myjokeapi.service.model.Joke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping(value = "/jokes")
public class MyJokeController {
    private static Logger log = LoggerFactory.getLogger(MyJokeController.class);

    @Autowired
    MyJokeService myJokeService;

    @GetMapping("/random")
    public JokeDto getRandom() {
        return myJokeService.getRandom();
    }

    @GetMapping("/{id}")
    public JokeDto findById(@PathVariable String id) {
        return myJokeService.findById(id);
    }

    @GetMapping("/byTerm")
    public List<JokeDto> findByTerm(String term) {
        return myJokeService.findByTerm(term);
    }

    @GetMapping("/all")
    public List<Joke> allJokes() {
        return myJokeService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Joke> addJoke(@RequestBody Joke newJoke) {
        //TODO: Error Handling
        Joke joke = myJokeService.addJoke(newJoke);
        if (joke == null) {
            throw new BadRequestException();
        } else {
            return new ResponseEntity<>(joke, HttpStatus.CREATED);
        }
    }

    @GetMapping("/category")
    public List<Joke> findCategory(String category) {
        return myJokeService.findCategory(category);
    }
}
