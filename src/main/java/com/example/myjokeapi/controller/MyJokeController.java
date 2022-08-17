package com.example.myjokeapi.controller;

import com.example.myjokeapi.exception.BadRequestException;
import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.service.MyJokeService;
import com.example.myjokeapi.database.model.JokeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.myjokeapi.interceptor.MyJokeInterceptor;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/jokes")
public class MyJokeController {
    private final MyJokeService myJokeService;

    //TODO: Change everything to ResponseEntity?

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
    public List<JokeEntity> allJokes() {
        return myJokeService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<JokeEntity> addJoke(@RequestBody JokeEntity newJoke) {
        //TODO: Error Handling
        JokeEntity jokeEntity = myJokeService.addJoke(newJoke);
        if (jokeEntity == null) {
            throw new BadRequestException();
        } else {
            return new ResponseEntity<>(jokeEntity, HttpStatus.CREATED);
        }
    }

    @GetMapping("/category")
    public List<JokeEntity> findCategory(String category) {
        return myJokeService.findCategory(category);
    }
}
