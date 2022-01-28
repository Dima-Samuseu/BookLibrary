package com.example.catalog.service.impl;

import com.example.catalog.model.Book;
import com.example.catalog.repository.BookRepository;
import com.example.catalog.service.BookService;
import com.example.catalog.util.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id) {
        return  bookRepository.findById(id).get();
    }

    @Override
    public List<Book> findAll() {
        return(List<Book>) bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
//        if (ModelUtils.idIsNotPresent(book.getId())) {
//            bookRepository.create(book);
//        } else {
//            bookRepository.update(book);
//        }
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
    bookRepository.deleteById(id);
    }
}
