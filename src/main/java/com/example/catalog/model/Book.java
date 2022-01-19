package com.example.catalog.model;

import com.example.catalog.util.BookDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize(using = BookDeserializer.class)
public class Book {

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
    
}
