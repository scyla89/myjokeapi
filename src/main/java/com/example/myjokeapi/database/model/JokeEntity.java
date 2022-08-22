package com.example.myjokeapi.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private String category;

    @Override
    public String toString() {
        return String.format("ID=%s, External ID=%s, Joke=%s, Category=%s", id, externalid, joke, category);
    }
}
