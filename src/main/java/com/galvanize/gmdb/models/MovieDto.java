package com.galvanize.gmdb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MovieDto {
    private Movie movie;
    private String foundBy;

    public MovieDto(Movie movie) {
        this.movie = movie;
    }

    public MovieDto(Movie movie, String foundBy) {
        this.movie = movie;
        this.foundBy = foundBy;
    }
}
