package com.example.catalog.external.openlibrary;

import com.example.catalog.external.openlibrary.config.OpenLibraryConfig;
import com.example.catalog.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.catalog.external.openlibrary.dto.ResponseOpenLibraryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenLibraryService {

    private final RestTemplate restTemplate;
    private final OpenLibraryConfig openLibraryConfig;

    public List<BookOpenLibraryDto> getByAuthor(String author) {

        ResponseOpenLibraryDto response;

        response = restTemplate.getForObject("https://openlibrary.org/search.json?author="
                + author, ResponseOpenLibraryDto.class);

        return response != null ? response.getOpenLibraryDtoList() : new ArrayList<>();
    }
}
