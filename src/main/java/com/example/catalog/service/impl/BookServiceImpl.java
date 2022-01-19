package com.example.catalog.service.impl;

import com.example.catalog.model.Book;
import com.example.catalog.service.BookService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private HashMap<Long, Book> bookHashMap = new HashMap<>();

    private void init() {
        bookHashMap.put(1L, new Book("5-5454-34343-34-1", "Артур в стране чудес", "Витек Пирожков", 368, 625, new BigDecimal("15.45")));
    }

    public HashMap<Long, Book> book() {
        if (bookHashMap.isEmpty()) {
            init();
            return bookHashMap;
        }
        return bookHashMap;
    }

    public HashMap<Long, Book> save(Book book) {
        if (bookHashMap.isEmpty()) {
            bookHashMap.put(1L, book);
            return bookHashMap;
        } else {
            bookHashMap.put(Collections.max(bookHashMap.keySet()) + 1, book);
            return bookHashMap;
        }
    }

    public HashMap<Long, Book> edit(Long id, Book book) {
        if (bookHashMap.containsKey(id))
            bookHashMap.put(id, book);
        return bookHashMap;
    }

    public HashMap<Long, Book> delete(Long id) {
        if (bookHashMap.containsKey(id))
            bookHashMap.remove(id);
        return bookHashMap;
    }
}
