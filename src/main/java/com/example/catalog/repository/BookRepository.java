package com.example.catalog.repository;

import com.example.catalog.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  {

    Book findById(Long id);

    List<Book> findAll();

    List<Book> findByAuthor(String author);

    void create(Book book);

    void update(Book book);

    void remove(Long id);
}
