package com.galvanize.gmdb.repository;


import com.galvanize.gmdb.models.Movie;
import com.galvanize.gmdb.models.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT new com.galvanize.gmdb.models.MovieDto(m) " +
            "FROM Movie m " +
            "WHERE m.isTrending = true")
    List<MovieDto> findAllByIsTrendingTrue();

    @Query("SELECT new com.galvanize.gmdb.models.MovieDto( m, " +
            "CASE " +
            "WHEN LOWER(m.title) LIKE LOWER(CONCAT('%', :query, '%')) THEN 'title' " +
            "WHEN LOWER(m.description) LIKE LOWER(CONCAT('%', :query, '%')) THEN 'description' " +
            "WHEN LOWER(m.director) LIKE LOWER(CONCAT('%', :query, '%')) THEN 'director' " +
            "WHEN LOWER(m.actors) LIKE LOWER(CONCAT('%', :query, '%')) THEN 'actors' " +
            "ELSE NULL END AS foundBy)" +
            "FROM Movie m " +
            "WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(m.description) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(m.director) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(m.actors) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<MovieDto> findAllByQueryString(String query, Pageable pageable);
}
