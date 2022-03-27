package com.example.catalog.controllers;

import com.example.catalog.dto.BookForAlfaBankDto;
import com.example.catalog.exception.BookTitleNotFoundException;
import com.example.catalog.exception.IncorrectDataException;
import com.example.catalog.model.Book;
import com.example.catalog.service.BookApfaBankService;
import com.example.catalog.service.BookOpenLibraryService;
import com.example.catalog.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private BookOpenLibraryService bookOpenLibraryService;
    private BookApfaBankService bookApfaBankService;

    @GetMapping("/get")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity
                .ok()
                .body(bookService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getOneBook (@PathVariable("id") Long id){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.findOne(id));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book requestBook) {
        bookService.create(requestBook);
        return ResponseEntity
                .status(201)
                .body(requestBook);
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<Book> editBook(@PathVariable(value = "id") long id,@Valid @RequestBody Book requestBook) {
        return ResponseEntity
                .ok()
                .body(bookService.update(requestBook, id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<List<Book>> deleteBookBook(@PathVariable(value = "id") long id) {
        bookService.remove(id);
        return ResponseEntity.ok().body(bookService.findAll());
    }



    @GetMapping({ "/{authorName}" })
    public ResponseEntity<List> getBooks(@PathVariable String authorName) {
        return ResponseEntity
                .ok()
                .body(bookOpenLibraryService.getBookDtoByAuthor(authorName));
    }

    @GetMapping("/price/{title}")
    public ResponseEntity<List<BookForAlfaBankDto>> getPrice(@PathVariable String title) {
        return ResponseEntity
                .ok()
                .body(bookApfaBankService.getBookDtoByTitle(title));
    }


}
