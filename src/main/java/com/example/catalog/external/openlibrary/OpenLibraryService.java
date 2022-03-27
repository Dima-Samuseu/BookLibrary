package com.example.catalog.external.openlibrary;

import com.example.catalog.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.catalog.external.openlibrary.dto.ResponseOpenLibraryDto;
import lombok.RequiredArgsConstructor;
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

    @Value("${open-library.url}")
    private String url;

    public List<BookOpenLibraryDto> getByAuthor(String author) {

        ResponseOpenLibraryDto response;

        response = restTemplate.getForObject(url + author, ResponseOpenLibraryDto.class);

        return response != null ? response.getOpenLibraryDtoList() : new ArrayList<>();
    }
}
