package com.example.myjokeapi.database.model;

import com.example.myjokeapi.connector.model.DadJoke;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="jokes")
public class JokeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String externalid;
    @Column
    private String joke;
    @Column
    @NotNull(message= "Please choose a category as well!")
    private String category;

    public JokeEntity() {}

    public JokeEntity(String externalid, String joke, String category) {
        this.externalid = externalid;
        this.joke = joke;
        this.category = category;
    }

    // DTO to Entity
    public JokeEntity(DadJoke dadJoke) {
        this.externalid = dadJoke.id();
        this.joke = dadJoke.joke();
        this.category = "dadjoke";
    }

    @Override
    public String toString() {
        return String.format("ID=%s, External ID=%s, Joke=%s, Category=%s", id, externalid, joke, category);
    }

    public long getId() {
        return id;
    }

    public String getExternalid() {
        return externalid;
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

    public void setExternalid(String externalId) {
        this.externalid = externalId;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
