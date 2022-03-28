package com.example.catalog.external.openlibrary;

import com.example.catalog.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.catalog.external.openlibrary.dto.ResponseOpenLibraryDto;
import com.example.catalog.model.Book;
import com.example.catalog.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OpenLibraryService {
    /**
     * The Rest template .
     */
    @Autowired
    @Qualifier("openLibrary")
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;


    @Value("${open-library.url}")
    private String url;

    public List<BookOpenLibraryDto> getByAuthor(String author) {

        List<BookOpenLibraryDto> responseList = getByAuthorOpenLibrary(author);
        log.info("Response open library = {}", responseList);
        List<Book> bookList = bookRepository.findByAuthor(author);
        log.info("Response database = {}", bookList);
        for (Book book : bookList) {
            ArrayList<String> isbn = new ArrayList<>();
            isbn.add(book.getIsbn());
            ArrayList<String> authorList = new ArrayList<>();
            authorList.add(book.getAuthor());
            responseList.add(new BookOpenLibraryDto(isbn, book.getName(), authorList));
        }
        return responseList;
    }

    public List<BookOpenLibraryDto> getByAuthorOpenLibrary(String author) {

        ResponseOpenLibraryDto response;
        response = restTemplate.getForObject(url + author, ResponseOpenLibraryDto.class);
        return response != null ? response.getOpenLibraryDtoList() : new ArrayList<>();
    }


}
