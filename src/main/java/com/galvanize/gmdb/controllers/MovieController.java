package com.galvanize.gmdb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.exception.BadRequestException;
import com.galvanize.gmdb.models.MovieDto;
import com.galvanize.gmdb.models.MovieResponse;
import com.galvanize.gmdb.models.Response;
import com.galvanize.gmdb.models.ResponseStatus;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    ObjectMapper objectMapper;

    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    /**
     * This method returns a list of movies.
     * accepts application/json
     * It is a placeholder method that currently returns an empty MovieResponse object.
     *
     * @return MovieResponse - an empty response object
     */
    @GetMapping(value = "/search",
            produces = "application/json")
    public MovieResponse getMoviesList(@RequestHeader(name = "mock", required = false) boolean mock,
                                       @RequestParam(name = "query", required = false) String query,
                                       @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                       @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) throws IOException {
        if(query != null && query.length() > 100) {
            throw new BadRequestException("Query parameter is too long. Maximum length is 100 characters.");
        }
        MovieResponse movieResponse = new MovieResponse();
        if (mock) {
            FileInputStream fileInputStream = new FileInputStream("movies.json");
            List<MovieDto> movies = Arrays.asList(objectMapper.readValue(fileInputStream, MovieDto[].class));
            Response responseSearchedMovies = new Response();
            responseSearchedMovies.setMovies(movies);
            ResponseStatus responseStatus = ResponseStatus.builder().status("success").build();
            responseSearchedMovies.setResponseStatus(responseStatus);
            movieResponse.setSearchedMovieResponse(responseSearchedMovies);
            return movieResponse;
        }
        return movieService.getMoviesList(query, pageNumber, limit);
    }
}
