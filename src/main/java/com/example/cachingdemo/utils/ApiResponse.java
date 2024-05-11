package com.example.cachingdemo.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class ApiResponse<T> {
    private String responseCode;
    private String message;
    private T payload;
    private List<String> error;

    public ApiResponse(String responseCode, String message, T payload, List<String> error) {
        this.responseCode = responseCode;
        this.message = message;
        this.payload = payload;
        this.error = error;
    }

    public ApiResponse(String responseCode, T payload, List<String> error) {
        this.responseCode = responseCode;
        this.payload = payload;
        this.error = error;
    }

    public ApiResponse(String responseCode, String message, T payload) {
        this.responseCode = responseCode;
        this.message = message;
        this.payload = payload;
    }


}
