package com.example.catalog.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {


    @Id
    private Long id;
    @NotBlank()
    private String isbn;
    @NotBlank()
    private String name;
    @NotBlank()
    private String author;
    @NotNull()
    private int numberOfPages;
    @NotNull()
    private int weight;
    @NotNull()
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
