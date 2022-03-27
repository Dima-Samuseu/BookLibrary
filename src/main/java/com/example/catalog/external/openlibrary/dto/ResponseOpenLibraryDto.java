package com.example.catalog.external.openlibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseOpenLibraryDto {
    @JsonProperty(value = "docs")
    private List<BookOpenLibraryDto> openLibraryDtoList;

    public ResponseOpenLibraryDto() {

        openLibraryDtoList = new ArrayList<>();
    }
}
