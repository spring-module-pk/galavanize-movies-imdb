package com.galvanize.gmdb.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaData {
    private int total;
    private int pageNumber;
    private int limit;
    private String next;
}
