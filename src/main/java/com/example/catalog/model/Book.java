package com.example.catalog.model;

import com.example.catalog.util.BookDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOK")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize(using = BookDeserializer.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Fill in the field \"isbn\"")
    private String isbn;
    @NotBlank(message = "Fill in the field \"name\"")
    private String name;
    @NotBlank(message = "Fill in the field \"author\"")
    private String author;
    @NotNull(message = "Fill in the field \"numberOfPages\"")
    private int numberOfPages;
    @NotNull(message = "Fill in the field \"weight\"")
    private int weight;
    @NotNull(message = "Fill in the field \"price\"")
    private BigDecimal price;

    public Book(String isbn, String name, String author, int numberOfPages, int weight, BigDecimal price) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.weight = weight;
        this.price = price;
    }
}
