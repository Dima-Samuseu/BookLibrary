package com.example.catalog.exception;

public class IncorrectDataException extends RuntimeException{

    public IncorrectDataException(String message) {
        super("incorrect request data: " + message);
    }
}
