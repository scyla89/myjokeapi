package com.example.myjokeapi.database.repository;

import com.example.myjokeapi.database.model.JokeEntity;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyJokeRepository extends CrudRepository<JokeEntity, String> {
    @Override
    List<JokeEntity> findAll();

    @Query(value = "SELECT * FROM jokes WHERE category = (:category)", nativeQuery = true)
    List<JokeEntity> findCategory(@Param String category);
}
