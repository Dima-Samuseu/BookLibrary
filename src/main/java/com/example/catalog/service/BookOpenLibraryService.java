package com.example.catalog.service;

import com.example.catalog.external.openlibrary.dto.BookOpenLibraryDto;

import java.util.List;

public interface BookOpenLibraryService {

    List<BookOpenLibraryDto> getBookDtoByAuthor(String author);
}
