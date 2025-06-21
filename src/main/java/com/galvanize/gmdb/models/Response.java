package com.galvanize.gmdb.models;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<MovieDto> movies;
    private ResponseStatus responseStatus;
}
