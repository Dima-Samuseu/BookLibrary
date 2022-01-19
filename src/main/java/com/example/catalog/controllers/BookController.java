package com.example.catalog.controllers;

import com.example.catalog.model.Book;
import com.example.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/getBook")
    public HashMap<Long, Book> getBook() {
        return bookService.book();
    }

    @PostMapping(path = "/addBook")
    @ResponseBody
    public HashMap<Long, Book> addBook(@Valid @RequestBody Book requestBook) {
        Book book = new Book(requestBook.getIsbn(), requestBook.getName(), requestBook.getAuthor(),
                requestBook.getNumberOfPages(), requestBook.getWeight(), requestBook.getPrice());
        bookService.save(book);
        return bookService.book();
    }

    @PutMapping(path = "/editBook/{id}")
    @ResponseBody
    public HashMap<Long, Book> editBook(@PathVariable(value = "id") long id,@Valid @RequestBody Book requestBook) {
        Book book = new Book(requestBook.getIsbn(), requestBook.getName(), requestBook.getAuthor(),
                requestBook.getNumberOfPages(), requestBook.getWeight(), requestBook.getPrice());
        bookService.edit(id, book);
        return bookService.book();
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    @ResponseBody
    public HashMap<Long, Book> deleteBookBook(@PathVariable(value = "id") long id) {
        bookService.delete(id);
        return bookService.book();
    }

}
