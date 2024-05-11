package com.example.cachingdemo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtils {
    public static <T> ResponseEntity createResponseWith(ApiResponseCodes codes, String message, T payload, HttpStatus httpStatus){
        return new ResponseEntity(new ApiResponse(codes.getCode(),codes.getMessage(),payload,null),httpStatus);
    }


}
