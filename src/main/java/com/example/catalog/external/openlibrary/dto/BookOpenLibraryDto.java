package com.example.catalog.external.openlibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Data
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
