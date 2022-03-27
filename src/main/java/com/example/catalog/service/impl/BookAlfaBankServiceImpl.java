package com.example.catalog.service.impl;

import com.example.catalog.dto.BookForAlfaBankDto;
import com.example.catalog.exception.BookTitleNotFoundException;
import com.example.catalog.external.alfabank.AlfaBankService;
import com.example.catalog.model.Book;
import com.example.catalog.service.BookApfaBankService;
import com.example.catalog.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookAlfaBankServiceImpl implements BookApfaBankService {

    private final BookService bookService;
    private final AlfaBankService alfaBankClient;

    @Override
    public List<BookForAlfaBankDto> getBookDtoByTitle(String title) throws BookTitleNotFoundException {
        List<BookForAlfaBankDto> bookDtoList = new ArrayList<>();
        List<Book> bookList = bookService.findByName(title);
        if (bookList.isEmpty()) {
            throw new BookTitleNotFoundException(title);
        }
        for (Book book : bookList) {
            bookDtoList.add(new BookForAlfaBankDto(book.getId(), book.getIsbn(), book.getName(), book.getAuthor(),
                    book.getNumberOfPages(), book.getWeight(), alfaBankClient.getCurrencyPrice(book.getPrice())))
            ;
        }

        return bookDtoList;
    }
}
