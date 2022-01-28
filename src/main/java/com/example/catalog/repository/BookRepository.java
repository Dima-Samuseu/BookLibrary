package com.example.catalog.repository;

import com.example.catalog.model.Book;
import org.springframework.data.repository.CrudRepository;

import javax.sql.DataSource;
import java.util.List;


public interface BookRepository extends CrudRepository<Book, Long> {

//    void setDataSource(DataSource dataSource);
//
//    Book getById(Long id);
//
//    List<Book> findAll();
//
//    void create(Book book);
//
//    void update(Book book);
//
//    void remove(Long id);
}
