package com.example.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForAlfaBankDto {

    private Long id;
    private String isbn;
    private String name;
    private String author;
    private int numberOfPages;
    private int weight;
    private Map<String, BigDecimal> priceInCurrency;
}
