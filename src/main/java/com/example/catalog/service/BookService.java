package com.example.catalog.service;

import com.example.catalog.model.Book;

import java.util.List;

public interface BookService {

    Book findOne(Long id);

    List<Book> findAll();

    List<Book> findByAuthor(String author);

    void save(Book book);

    void remove(Long id);
}
