package com.example.myjokeapi.database.model;

import com.example.myjokeapi.connector.model.DadJoke;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="jokes")
public class JokeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column
    private String externalId;
    @Column
    private String joke;
    @Column
    @NotNull(message= "Please choose a category as well!")
    private String category;

    public JokeEntity() {}

    public JokeEntity(String externalId, String joke, String category) {
        this.externalId = externalId;
        this.joke = joke;
        this.category = category;
    }

    // DTO to Entity
    public JokeEntity(DadJoke dadJoke) {
        this.externalId = dadJoke.id();
        this.joke = dadJoke.joke();
        this.category = "dadjoke";
    }

    @Override
    public String toString() {
        return String.format("ID=%s, External ID=%s, Joke=%s, Category=%s", id, externalId, joke, category);
    }

    public long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getJoke() {
        return joke;
    }

    public String getCategory() {
        return category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
