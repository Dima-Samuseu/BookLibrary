package com.example.catalog.controllers;

import com.example.catalog.exception.EntityNotFoundException;
import com.example.catalog.model.Book;
import com.example.catalog.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class BookControllerTest {

    @MockBean
    private BookService service;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenBooks_whenGetBooks_thenStatus200() throws Exception {
        Book bookOne = new Book(1L, "34-45", "name", "Michail", 560, 600, new BigDecimal(4.5));
        Book bookTwo = new Book(2L, "34-46", "Tor", "Carlos", 560, 600, new BigDecimal(4.5));

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(bookOne, bookTwo));

        mockMvc.perform(
                        get("/getBook"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(bookOne, bookTwo))));
    }

    @Test
    public void givenId_whenGetExistingBook_thenStatus200andBookReturned() throws Exception {

        Book book = new Book(1L, "34-45", "name", "Michail", 560, 600, new BigDecimal(4.5));
        Mockito.when(service.findOne(Mockito.any())).thenReturn(book);

        mockMvc.perform(
                        get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.isbn").value("34-45"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.author").value("Michail"))
                .andExpect(jsonPath("$.numberOfPages").value("560"))
                .andExpect(jsonPath("$.weight").value("600"))
                .andExpect(jsonPath("$.price").value("4.5"));
    }


    @Test
    public void givenId_whenGetNotExistingBook_thenStatus404anExceptionThrown() throws Exception {


        Mockito.when(service.findOne(Mockito.any())).
                thenReturn(new Book());

        mockMvc.perform(
                        get("/0"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> EntityNotFoundException.class.equals(mvcResult.getResolvedException().getClass()));
    }

    @Test
    public void givenBook_whenAdd_thenStatus201() throws Exception {

        Book book = new Book("34-45", "name", "Michail", 560, 600, new BigDecimal(4.5));


        mockMvc.perform(
                        post("/addBook")
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }


    @Test
    public void giveBook_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {

        Book book = new Book(1L, "34-45", "name", "Michail", 560, 600, new BigDecimal(4.5));
        Mockito.when(service.findOne(Mockito.any())).thenReturn(book);

        mockMvc.perform(
                        put("/editBook/1")
                                .content(objectMapper.writeValueAsString(new Book("34-45", "Tor", "Vasya", 400, 500, new BigDecimal(4.5))))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.isbn").value("34-45"))
                .andExpect(jsonPath("$.name").value("Tor"))
                .andExpect(jsonPath("$.author").value("Vasya"))
                .andExpect(jsonPath("$.numberOfPages").value("400"))
                .andExpect(jsonPath("$.weight").value("500"))
                .andExpect(jsonPath("$.price").value("4.5"));
    }

    @Test
    public void givenBook_whenDeleteBook_thenStatus200() throws Exception {

        Book book = new Book(1L, "34-45", "name", "Michail", 560, 600, new BigDecimal(4.5));
        Mockito.when(service.findOne(Mockito.any())).thenReturn(book);

        mockMvc.perform(
                        delete("/deleteBook/1"))
                .andExpect(status().isOk());
    }

}