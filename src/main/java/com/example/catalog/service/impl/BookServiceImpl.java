package com.example.catalog.service.impl;

import com.example.catalog.exception.IncorrectDataException;
import com.example.catalog.model.Book;
import com.example.catalog.repository.BookRepository;
import com.example.catalog.service.BookService;
import com.example.catalog.util.ModelUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id) throws IncorrectDataException {
        if (ModelUtils.idIsNotPresent(id))
            throw new IncorrectDataException("id-" + id);
        return bookRepository.getOneBook(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.getBook();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Book create(Book requestBook) {
        bookRepository.create(requestBook);
        return requestBook;
    }

    @Override
    public Book update(Book requestBook, Long id) {
        Book book = bookRepository.getOneBook(id);
        book.setIsbn(requestBook.getIsbn());
        book.setName(requestBook.getName());
        book.setAuthor(requestBook.getAuthor());
        book.setNumberOfPages(requestBook.getNumberOfPages());
        book.setWeight(requestBook.getWeight());
        book.setPrice(requestBook.getPrice());
        bookRepository.update(book);
        return book;
    }

    @Override
    public void remove(Long id) {
        if (ModelUtils.idIsNotPresent(id)) {
            throw new IncorrectDataException("id-" + id);
        }
        bookRepository.remove(id);
    }
}
