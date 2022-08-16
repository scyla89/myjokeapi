package com.example.myjokeapi.service;

import com.example.myjokeapi.service.model.Joke;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyJokeRepository extends CrudRepository<Joke, String> {
    @Override
    List<Joke> findAll();

    //TODO: Create a custom query
    //@Query(value = "SELECT * FROM jokes WHERE category = (:category)", nativeQuery = true)
    //List<Joke> selectCategory(@Param String category);
}
