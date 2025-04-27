package com.hotelsbook.reviews;

import org.springframework.http.HttpStatus;

// Code from another microservice
public class ErrorResponse {
    private int status;
    private String description;

    public ErrorResponse(int status, String description) {
        this.status = status;
        this.description = description;
    }

    // I love overloading constructors
    public ErrorResponse(HttpStatus status, String description) {
        this.status = status.value();
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
