package com.galvanize.gmdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.StarterApplication;
import com.galvanize.gmdb.exception.BadRequestException;
import com.galvanize.gmdb.models.MetaData;
import com.galvanize.gmdb.models.Movie;
import com.galvanize.gmdb.models.MovieDto;
import com.galvanize.gmdb.models.MovieResponse;
import com.galvanize.gmdb.models.Response;
import com.galvanize.gmdb.models.ResponseStatus;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StarterApplication.class)

@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MovieService movieService;

    @Test
    void getMoviesList() throws Exception {
        Response response = new Response();
        MovieDto movieDto1 = MovieDto.builder().movie(new Movie(1L, "Movie1", "Ramanand Sagar", "Shobha", "Action Movie", 2023, 5)).foundBy("title").build();
        MovieDto movieDto2 = MovieDto.builder().movie(new Movie(2L, "Movie2", "Karan Johar", "Shobha", "Romantic Movie", 2025, 4)).foundBy("title").build();
        response.setMovies(List.of(movieDto1, movieDto2));
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTrendingMovieResponse(response);
        movieResponse.setSearchedMovieResponse(response);
        movieResponse.setSearchedMetaData(MetaData.builder().
                total(20).
                limit(10).
                pageNumber(1).
                next("/movies/search?query=ABC&pageNumber=11&limit=10").build());
        when(movieService.getMoviesList("ABC", 1, 10)).thenReturn(movieResponse);
        MvcResult mvcResult = mockMvc.perform(get("/movies/search").
                param("query", "ABC").
                param("pageNumber", "1").
                param("limit", "10")).andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertEquals(200, mvcResult.getResponse().getStatus(), "Expected status code 200 but got " + mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("trendingMovieResponse"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("searchedMovieResponse"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("searchedMetaData"));
    }

    @Test
    void getMockMovieResponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/movies/search").header("mock", true)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.searchedMovieResponse.movies").isArray()).andReturn();

        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
        Assertions.assertTrue(mvcResult.getResponse().getStatus() == 200, "Expected status code 200 but got " + mvcResult.getResponse().getStatus());
    }

    @Test
    void getMockMovieResponseAndVerifyValues() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/movies/search").header("mock", true)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.searchedMovieResponse.movies").isArray()).
                andExpect(jsonPath("$.searchedMovieResponse.movies[0].movie.title").value("The Avengers")).andReturn();

        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
        assertEquals(200, mvcResult.getResponse().getStatus(), "Expected status code 200 but got " + mvcResult.getResponse().getStatus());
    }

    @Test
    void shouldReturnTrendingMovieListWhenSearchWithEmptyString() throws Exception {
        setUpMock();
        MvcResult mvcResult = mockMvc.perform(get("/movies/search?query=")).andReturn();
        assertions(mvcResult);

    }

    private void assertions(MvcResult mvcResult) throws JsonProcessingException, UnsupportedEncodingException {
        assertEquals(200, mvcResult.getResponse().getStatus());
        MovieResponse movieResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MovieResponse.class);
        assertNotNull(movieResponse, "Expected MovieResponse to be not null but got null");
        assertNull(movieResponse.getSearchedMovieResponse());
        assertNotNull(movieResponse.getTrendingMovieResponse());
        assertNotNull(movieResponse.getTrendingMovieResponse().getMovies());
        assertFalse(movieResponse.getTrendingMovieResponse().getMovies().isEmpty());
    }

    private void setUpMock() {
        MovieResponse mockMovieResponse = new MovieResponse();
        Response response = new Response();
        MovieDto movieDto1 = MovieDto.builder().movie(new Movie(1L, "Movie1", "Ramanand Sagar", "Shobha", "Action Movie", 2023, 5)).foundBy("title").build();
        MovieDto movieDto2 = MovieDto.builder().movie(new Movie(2L, "Movie2", "Karan Johar", "Shobha", "Romantic Movie", 2025, 4)).foundBy("title").build();
        response.setMovies(List.of(movieDto1, movieDto2));
        mockMovieResponse.setTrendingMovieResponse(response);
        when(movieService.getMoviesList("", 0, 10)).thenReturn(mockMovieResponse);
    }

    @Test
    void seachMovieReturnsValidJSONResponse() throws Exception {
        MovieResponse mockMovieResponse = new MovieResponse();
        when(movieService.getMoviesList("abc", 0, 10)).thenReturn(mockMovieResponse);
        MvcResult response = mockMvc
                .perform(get("/movies/search").param("query", "abc"))
                .andExpect(status().isOk())
                .andReturn();


        String content = response.getResponse().getContentAsString();
        // test case will get updated once the search functionality is implemented
        assertEquals(mockMovieResponse, objectMapper.readValue(content, MovieResponse.class));
    }

    @Test()
    void seachMovieThrowsExceptionWhenTitleIsEmptyOrNull() throws Exception {
        when(movieService.getMoviesList("", 0, 10))
                .thenThrow(new RuntimeException("Internal Server Error: Title parameter is required for movie search."));
        MvcResult response = mockMvc
                .perform(get("/movies/search").param("query", ""))
                .andExpect(status().is5xxServerError())
                .andReturn();

        assertThat(response.getResolvedException())
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Internal Server Error: Title parameter is required for movie search.");
    }

    @Test()
    void seachMovieThrowsExceptionForBadRequest() throws Exception {
        when(movieService.getMoviesList("invalid", 0, 10))
                .thenThrow(new BadRequestException("Invalid title parameter provided."));
        MvcResult response = mockMvc.perform(get("/movies/search").param("query", "invalid"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertThat(response.getResolvedException())
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Invalid title parameter provided.");
    }

    @Test()
    void noSearchResultFoundFromService() throws Exception {
        // Mock trending movies
        Response trendingResponse = new Response();
        MovieDto movieDto1 = MovieDto.builder().movie(new Movie(1L, "Movie1", "Ramanand Sagar", "Shobha", "Action Movie", 2023, 5)).foundBy("title").build();
        MovieDto movieDto2 = MovieDto.builder().movie(new Movie(2L, "Movie2", "Karan Johar", "Shobha", "Romantic Movie", 2025, 4)).foundBy("title").build();
        trendingResponse.setMovies(List.of(movieDto1, movieDto2));
        // Mock searched movies (empty)
        Response searchedResponse = new Response();
        searchedResponse.setMovies(List.of());
        searchedResponse.setResponseStatus(ResponseStatus.builder()
                .status("400")
                .message("No movies found for the given query: noMatchFound")
                .build());

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTrendingMovieResponse(trendingResponse);
        movieResponse.setSearchedMovieResponse(searchedResponse);

        when(movieService.getMoviesList("noMatchFound", 0, 10)).thenReturn(movieResponse);

        // Perform the request
        MvcResult result = mockMvc.perform(get("/movies/search").param("query", "noMatchFound"))
                .andExpect(status().isOk())
                .andReturn();

        // Parse the response
        MovieResponse actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), MovieResponse.class);

        // Validate searchedMovies is empty
        assertNotNull(actualResponse.getSearchedMovieResponse());
        assertTrue(actualResponse.getSearchedMovieResponse().getMovies().isEmpty(), "Expected searchedMovies to be empty");
        assertEquals("400", actualResponse.getSearchedMovieResponse().getResponseStatus().getStatus());
        assertEquals("No movies found for the given query: noMatchFound", actualResponse.getSearchedMovieResponse().getResponseStatus().getMessage());

        // Validate trendingMovies is not empty
        assertNotNull(actualResponse.getTrendingMovieResponse());
        assertFalse(actualResponse.getTrendingMovieResponse().getMovies().isEmpty(), "Expected trendingMovies to be non-empty");
        assertEquals(2, actualResponse.getTrendingMovieResponse().getMovies().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}) // 2 and 50 characters
    public void shouldReturnResponseForValidQueryString(String query) {
        when(movieService.getMoviesList(query, 1, 10)).thenReturn(new MovieResponse());
        try {
            MvcResult mvcResult = mockMvc.perform(get("/movies/search").param("query", query))
                    .andExpect(status().isOk())
                    .andReturn();
            assertNotNull(mvcResult.getResponse().getContentAsString());
            assertEquals(200, mvcResult.getResponse().getStatus(), "Expected status code 200 but got " + mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            Assertions.fail("Exception should not be thrown for valid query string");
        }
    }

    @Test
    @DisplayName("when queryString have more than 100 character then it should return 400 Bad Request")
    public void shouldReturn400whenQueryStringhaveMoreThan100characterController() {
        String query = "a".repeat(101); // 101 characters long
        try {
            mockMvc.perform(get("/movies/search").param("query", query))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            Assertions.fail("Query parameter is too long. Maximum length is 100 characters.");
        }
    }
}