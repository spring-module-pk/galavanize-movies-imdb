package com.galvanize.gmdb.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private Long id;
    private String title;
    private String director;
    private String actors;
    private String description;
    private int release;
    private double rating;
    private boolean isTrending;


    public Movie() {
        // Default constructor
    }

    public Movie(Long id, String title, String director, String actor, String description, int release, int rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.actors = actor;
        this.description = description;
        this.release = release;
        this.rating = rating;
    }
}
