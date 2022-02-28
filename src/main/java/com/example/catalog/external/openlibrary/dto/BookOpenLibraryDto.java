package com.example.catalog.external.openlibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOpenLibraryDto {

    @JsonProperty(value = "isbn")
    private ArrayList<String> isbn;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "author_name")
    private ArrayList<String> author;
}
