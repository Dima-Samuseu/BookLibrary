package com.example.catalog.controllers;

import com.example.catalog.exception.EntityNotFoundException;
import com.example.catalog.model.Book;
import com.example.catalog.service.BookService;
import com.example.catalog.util.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/getBook")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> bookList =  bookService.findAll();
        return ResponseEntity.ok().body(bookList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getOneBook (@PathVariable("id") Long id) throws EntityNotFoundException{
        if (ModelUtils.idIsNotPresent(id))
            throw new EntityNotFoundException("id-" + id);
        Book book = bookService.findOne(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(book);
    }

    @PostMapping(path = "/addBook")
    @ResponseBody
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book requestBook) {
        Book book = new Book(requestBook.getIsbn(), requestBook.getName(), requestBook.getAuthor(),
                requestBook.getNumberOfPages(), requestBook.getWeight(), requestBook.getPrice());
        bookService.save(book);
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping(path = "/editBook/{id}")
    @ResponseBody
    public ResponseEntity<Book> editBook(@PathVariable(value = "id") long id,@Valid @RequestBody Book requestBook) throws EntityNotFoundException {
        Book book = bookService.findOne(id);
        book.setIsbn(requestBook.getIsbn());
        book.setName(requestBook.getName());
        book.setAuthor(requestBook.getAuthor());
        book.setNumberOfPages(requestBook.getNumberOfPages());
        book.setWeight(requestBook.getWeight());
        book.setPrice(requestBook.getPrice());
        bookService.save(book);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    @ResponseBody
    public ResponseEntity<List<Book>> deleteBookBook(@PathVariable(value = "id") long id) throws EntityNotFoundException{
        if (ModelUtils.idIsNotPresent(id))
            throw new EntityNotFoundException("id-" + id);
        bookService.remove(id);
        return ResponseEntity.ok().body(bookService.findAll());
    }

}
