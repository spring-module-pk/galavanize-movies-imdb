package com.galvanize.gmdb.service;

import com.galvanize.gmdb.models.Movie;
import com.galvanize.gmdb.models.MovieDto;
import com.galvanize.gmdb.models.MovieResponse;
import com.galvanize.gmdb.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    MovieService movieService;

    @ParameterizedTest
    @MethodSource("provideStringsForTest")
    void shouldgetMoviesListWhenSearchQueryIsEmpty(String query) {
        MovieDto movieDto1 = MovieDto.builder().movie(new Movie(1L, "Movie1", "Ramanand Sagar", "Shobha", "Action Movie", 2023, 5)).foundBy("title").build();
        MovieDto movieDto2 = MovieDto.builder().movie(new Movie(2L, "Movie2", "Karan Johar", "Shobha", "Romantic Movie", 2025, 4)).foundBy("title").build();
        when(movieRepository.findAllByIsTrendingTrue()).thenReturn(List.of(movieDto1, movieDto2));
        MovieResponse response = movieService.getMoviesList(query, 0, 10);
        assertNotNull(response);
        assertNotNull(response.getTrendingMovieResponse());
        assertEquals("success", response.getTrendingMovieResponse().getResponseStatus().getStatus());
        assertEquals("trending movies fetched successfully", response.getTrendingMovieResponse().getResponseStatus().getMessage());
        assertTrue(response.getTrendingMovieResponse().getMovies().size() > 0);
    }

    private static List<String> provideStringsForTest() {
        return Arrays.asList("", " ", null);
    }

    @Test
    void shouldThrowExceptionWhenDatabaseIsDown() {
        String query = "";
        when(movieRepository.findAllByIsTrendingTrue()).thenThrow(new RuntimeException("Database is down"));
        assertThrows(RuntimeException.class, () -> {
            movieService.getMoviesList(query, 0, 10);
        });
    }


    @Test
    void shouldReturnTrendingMoviesWhenQueryDoesNotMatch() {
        // Arrange
        String query = "NonMatchingQuery";
        when(movieRepository.findAllByQueryString(anyString(), any(Pageable.class))).thenReturn(Page.empty());
        MovieDto movieDto1 = MovieDto.builder().movie(new Movie(1L, "Movie1", "director1", "Actor1", "Action Movie", 2023, 5)).foundBy("title").build();
        MovieDto movieDto2 = MovieDto.builder().movie(new Movie(2L, "Movie2", "director2", "Actor2", "Romantic Movie", 2025, 4)).foundBy("title").build();
        when(movieRepository.findAllByIsTrendingTrue()).thenReturn(List.of(movieDto1, movieDto2));
        // Act
        MovieResponse response = movieService.getMoviesList(query, 1, 10);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getTrendingMovieResponse());
        assertTrue(response.getTrendingMovieResponse().getMovies().size() > 0);
        assertEquals(response.getSearchedMovieResponse().getMovies().size(), 0);
        assertTrue(response.getSearchedMovieResponse().getResponseStatus().getMessage().contains("No movies found for the given query: " + query));
    }

    @Test
    void shouldReturnSearchedMoviesWhenQueryMatch() {
        // Arrange
        String query = "MatchingQuery";

        Page<MovieDto> mockPage = Mockito.mock(Page.class);
        MovieDto movieDto1 = MovieDto.builder().movie(new Movie(1L, "Movie1", "director1", "Actor1", "Action Movie", 2023, 5)).foundBy("title").build();
        MovieDto movieDto2 = MovieDto.builder().movie(new Movie(2L, "Movie2", "director2", "Actor2", "Romantic Movie", 2025, 4)).foundBy("title").build();
        when(mockPage.getContent()).thenReturn(List.of(movieDto1, movieDto2 ));
        when(movieRepository.findAllByQueryString(anyString(), any(Pageable.class))).thenReturn(mockPage);

        // Act
        MovieResponse response = movieService.getMoviesList(query, 1, 10);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getSearchedMovieResponse());
        assertTrue(response.getSearchedMovieResponse().getMovies().size() > 0);
        assertTrue(response.getSearchedMovieResponse().getResponseStatus().getMessage().contains("Movies found for the query: " + query));
        assertEquals(10, response.getSearchedMetaData().getLimit());
        assertEquals(2, response.getSearchedMetaData().getPageNumber());
        assertEquals("/movies/search?query=MatchingQuery&pageNumber=2&limit=10", response.getSearchedMetaData().getNext());
    }
}
