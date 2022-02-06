package com.example.catalog.service;

import com.example.catalog.model.Book;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class OpenLibraryService {

    public List getBooks(String searchString) {
        String url = "https://openlibrary.org/search.json?q=" + searchString;
        //String url = "https://openlibrary.org/authors/" + searchString + "/works.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RestTemplate restTemplate = new RestTemplate();
        try {
            String bookSearch = restTemplate.getForObject(url, String.class);
            System.out.println(bookSearch);
            List<String> bookList = JsonPath.read(bookSearch, "$.docs[*].title");
            return bookList;
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
