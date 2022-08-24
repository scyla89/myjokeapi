package com.example.myjokeapi.controller;

import com.example.myjokeapi.controller.model.JokeDto;
import com.example.myjokeapi.service.MyJokeService;
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

    @GetMapping("/random")
    public JokeDto getRandom() {
        return myJokeService.getRandom();
    }

    @GetMapping("/{id}")
    public JokeDto findById(@PathVariable String id) {
        return myJokeService.findById(id);
    }

    @GetMapping("/")
    public List<JokeDto> searchFor(
        @RequestParam("term") Optional<String> term,
        @RequestParam("category") Optional<String> category) {
        List<JokeDto> jokeDtoList = new ArrayList<>();
        if (term.isPresent()) {
            jokeDtoList.addAll(myJokeService.findByTerm(term.get()));
        } else if (category.isPresent()){
            jokeDtoList.addAll(myJokeService.findCategory(category.get()));
        } else {
            jokeDtoList.addAll(myJokeService.findAll());
        }
        return jokeDtoList;
    }

    @PostMapping("/")
    public ResponseEntity<JokeDto> addJoke(@RequestBody JokeDto newJokeDto) {
        JokeDto jokeDto = myJokeService.addJoke(newJokeDto);
        return new ResponseEntity<>(jokeDto, HttpStatus.CREATED);
    }
}
