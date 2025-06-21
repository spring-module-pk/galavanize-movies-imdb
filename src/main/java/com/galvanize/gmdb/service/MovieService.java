package com.galvanize.gmdb.service;

import com.galvanize.gmdb.models.MetaData;
import com.galvanize.gmdb.models.Movie;
import com.galvanize.gmdb.models.MovieDto;
import com.galvanize.gmdb.models.MovieResponse;
import com.galvanize.gmdb.models.Response;
import com.galvanize.gmdb.models.ResponseStatus;
import com.galvanize.gmdb.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    public static final String MOVIES_SEARCH_QUERY = "/movies/search?query=";
    public static final String PAGE_NUMBER = "&pageNumber=";
    public static final String LIMIT = "&limit=";
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private MovieResponse getTrendingMovies() {
        MovieResponse movieResponse = new MovieResponse();
        Response response = new Response();
        response.setMovies(movieRepository.findAllByIsTrendingTrue());
        response.setResponseStatus(ResponseStatus.builder().status("success").message("trending movies fetched successfully").build());
        movieResponse.setTrendingMovieResponse(response);
        return movieResponse;
    }

    private MovieResponse buildSearchedMovieResponse(List<MovieDto> movies,
                                                     String status,
                                                     String message,
                                                     String query,
                                                     int pageNumber,
                                                     int limit) {
        Response response = new Response();
        response.setMovies(movies);
        response.setResponseStatus(ResponseStatus.builder()
                .status(status)
                .message(message)
                .build());
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setSearchedMovieResponse(response);
        movieResponse.setSearchedMetaData(MetaData.builder()
                .limit(limit)
                .pageNumber(pageNumber+1)
                .total(movies.size())
                .next(MOVIES_SEARCH_QUERY + query + PAGE_NUMBER + (pageNumber +1) + LIMIT + limit).build());
        return movieResponse;
    }

    public MovieResponse getMoviesList(String query,
                                       int pageNumber,
                                       int limit) {
        if (query == null || query.trim().isEmpty()) {
            return getTrendingMovies();
        }else {
            return getSearchedMovies(query, pageNumber, limit);
        }
    }

    private MovieResponse getSearchedMovies(String query,
                                            int pageNumber,
                                            int limit) {

        Pageable pageable = PageRequest.of(pageNumber, limit);
        Page<MovieDto> moviePage = movieRepository.findAllByQueryString(query, pageable);
        List<MovieDto> searchedMovies = moviePage.getContent();
        if (searchedMovies.isEmpty()) {
            log.error("No movies found for the query: {}, pageNumber: {} and limit: {}", query, pageNumber, limit);
            MovieResponse movieResponse = buildSearchedMovieResponse(
                    searchedMovies, "400", "No movies found for the given query: " + query, query, pageNumber, limit
            );
            Response trendingResponse = new Response();
            trendingResponse.setMovies(movieRepository.findAllByIsTrendingTrue());
            movieResponse.setTrendingMovieResponse(trendingResponse);
            return movieResponse;
        }
        return buildSearchedMovieResponse(searchedMovies, "success", "Movies found for the query: " + query, query, pageNumber, limit);
    }
}
