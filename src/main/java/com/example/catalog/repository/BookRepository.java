package com.example.catalog.repository;

import com.example.catalog.model.Book;

import java.util.List;

public interface BookRepository  {

    Book getOneBook(Long id);

    List<Book> getBook();

    List<Book> findByAuthor(String author);

    List<Book> findByName(String name);

    void create(Book book);

    void update(Book book);

    void remove(Long id);
}
