package com.galvanize.gmdb.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ResponseStatus {
    private String status;
    private String message;
    @Builder
    public ResponseStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
