package com.example.catalog.exception;

public class BookTitleNotFoundException extends RuntimeException {

    public BookTitleNotFoundException(String message) {
        super("Книги с названием " + message + " не найдено" );
    }
}
