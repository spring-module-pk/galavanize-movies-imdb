package com.galvanize.gmdb.models;

import lombok.Data;

@Data
public class MovieResponse {
    private Response trendingMovieResponse;
    private Response searchedMovieResponse;
    private MetaData searchedMetaData;
}
