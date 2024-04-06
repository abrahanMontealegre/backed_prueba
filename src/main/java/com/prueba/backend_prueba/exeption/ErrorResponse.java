package com.prueba.backend_prueba.exeption;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String error;
    private String message;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    // Getters and setters
}