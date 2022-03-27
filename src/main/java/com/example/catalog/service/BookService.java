package com.example.catalog.service;

import com.example.catalog.exception.IncorrectDataException;
import com.example.catalog.model.Book;

import java.util.List;

public interface BookService {

    Book findOne(Long id) throws IncorrectDataException;

    List<Book> findAll();

    List<Book> findByAuthor(String author);

    List<Book> findByName(String name);

    Book create(Book book);

    Book update(Book book, Long id);

    void remove(Long id);
}
