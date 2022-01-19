package com.example.catalog.service;

import com.example.catalog.model.Book;

import java.util.HashMap;

public interface BookService {

    HashMap<Long, Book> book();

    HashMap<Long, Book> save(Book book);

    HashMap<Long, Book> edit(Long id, Book book);

    HashMap<Long, Book> delete(Long id);
}
