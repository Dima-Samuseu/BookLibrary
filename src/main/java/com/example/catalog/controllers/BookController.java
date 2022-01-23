package com.example.catalog.controllers;

import com.example.catalog.model.Book;
import com.example.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/getBook")
    public ArrayList<Book> getBook() {
        return (ArrayList<Book>) bookService.findAll();
    }

    @PostMapping(path = "/addBook")
    @ResponseBody
    public ArrayList<Book> addBook(@Valid @RequestBody Book requestBook) {
        Book book = new Book(requestBook.getIsbn(), requestBook.getName(), requestBook.getAuthor(),
                requestBook.getNumberOfPages(), requestBook.getWeight(), requestBook.getPrice());
        bookService.save(book);
        return (ArrayList<Book>) bookService.findAll();
    }

    @PutMapping(path = "/editBook/{id}")
    @ResponseBody
    public ArrayList<Book> editBook(@PathVariable(value = "id") long id,@Valid @RequestBody Book requestBook) {
        Book book = bookService.findOne(id);
        book.setIsbn(requestBook.getIsbn());
        book.setName(requestBook.getName());
        book.setAuthor(requestBook.getAuthor());
        book.setNumberOfPages(requestBook.getNumberOfPages());
        book.setWeight(requestBook.getWeight());
        book.setPrice(requestBook.getPrice());
        bookService.save(book);
        return (ArrayList<Book>) bookService.findAll();
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    @ResponseBody
    public ArrayList<Book> deleteBookBook(@PathVariable(value = "id") long id) {
        bookService.remove(id);
        return (ArrayList<Book>) bookService.findAll();
    }

}
