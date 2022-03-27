package com.example.catalog.service;

import com.example.catalog.dto.BookForAlfaBankDto;
import com.example.catalog.exception.BookTitleNotFoundException;
import com.example.catalog.external.openlibrary.dto.BookOpenLibraryDto;

import java.util.List;

public interface BookApfaBankService {

    List<BookForAlfaBankDto> getBookDtoByTitle(String title) throws BookTitleNotFoundException;
}
