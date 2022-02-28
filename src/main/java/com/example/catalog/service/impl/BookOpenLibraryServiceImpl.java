package com.example.catalog.service.impl;


import com.example.catalog.external.openlibrary.OpenLibraryService;
import com.example.catalog.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.catalog.model.Book;
import com.example.catalog.service.BookOpenLibraryService;
import com.example.catalog.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Service
public class BookOpenLibraryServiceImpl implements BookOpenLibraryService {

    private final BookService bookService;
    private final OpenLibraryService openLibraryService;

    @Override
    public List<BookOpenLibraryDto> getBookDtoByAuthor(String author) {
        List<BookOpenLibraryDto> bookOpenLibraryDtoList = openLibraryService.getByAuthor(author);
        bookOpenLibraryDtoList.addAll(getBookDtoDb(author));
        return bookOpenLibraryDtoList;
    }

    private List<BookOpenLibraryDto> getBookDtoDb(String author) {

        List<Book> bookListDb = bookService.findByAuthor(author);
        List<BookOpenLibraryDto> bookOpenLibraryDtoList = new ArrayList<>();
        for (Book book : bookListDb) {
            bookOpenLibraryDtoList.add(new BookOpenLibraryDto(
                    new ArrayList<>(Collections.singleton(book.getIsbn())),
                    book.getName(),
                    new ArrayList<>(Collections.singleton(book.getAuthor()))
            ));
        }
        return bookOpenLibraryDtoList;
    }
}
