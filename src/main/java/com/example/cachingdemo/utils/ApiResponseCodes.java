package com.example.cachingdemo.utils;

import lombok.*;


public enum ApiResponseCodes {

    ALL_GOOD("1000","All Good");

    private String code;
    private String message;

    ApiResponseCodes(String  code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String  code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
