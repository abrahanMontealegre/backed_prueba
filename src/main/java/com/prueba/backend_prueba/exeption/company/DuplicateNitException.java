package com.prueba.backend_prueba.exeption.company;

public class DuplicateNitException extends RuntimeException {
    public DuplicateNitException(String message) {
        super(message);
    }
}
