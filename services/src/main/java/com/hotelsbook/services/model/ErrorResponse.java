package com.hotelsbook.services.model;

public class ErrorResponse {
    private int code;
    private String description;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.description = message;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
