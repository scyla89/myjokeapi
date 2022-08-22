package com.example.myjokeapi.controller;

import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.service.MyJokeService;
import com.example.myjokeapi.database.model.JokeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/jokes")
public class MyJokeController {
    private final MyJokeService myJokeService;

    @GetMapping("/")
    public List<JokeDto> getJoke(
            @RequestParam("id") Optional<String> id,
            @RequestParam("term") Optional<String> term,
            @RequestParam("category") Optional<String> category,
            @RequestParam(value="random", defaultValue="false") Optional<Boolean> random) {
        List<JokeDto> jokeDtoList = new ArrayList<>();
        if (id.isPresent()) {
            jokeDtoList.add(myJokeService.findById(id.get()));
        } else if (term.isPresent()) {
            jokeDtoList.addAll(myJokeService.findByTerm(term.get()));
        } else if (category.isPresent()){
            jokeDtoList.addAll(myJokeService.findCategory(category.get()));
        } else if (random.isPresent() && random.get()){
            jokeDtoList.add(myJokeService.getRandom());
        } else {
            jokeDtoList.addAll(myJokeService.findAll());
        }
        return jokeDtoList;
    }

    @PostMapping("/")
    public ResponseEntity<JokeEntity> addJoke(@RequestBody JokeEntity newJoke) {
        JokeEntity jokeEntity = myJokeService.addJoke(newJoke);
        return new ResponseEntity<>(jokeEntity, HttpStatus.CREATED);
    }
}
