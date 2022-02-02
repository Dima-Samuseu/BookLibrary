package com.example.catalog.repository.impl;

import com.example.catalog.model.Book;
import com.example.catalog.repository.BookRepository;
import org.apache.catalina.Service;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BookRepositoryImplTest {

    private EmbeddedDatabase embeddedDatabase;
    @Mock
    JdbcTemplate jdbcTemplate;

    private BookRepository bookRepository;

    @Before
    public void setUp() {
        // Создадим базу данных для тестирования
//        embeddedDatabase = new EmbeddedDatabaseBuilder()
//                .addDefaultScripts()// Добавляем скрипты schema.sql и data.sql
//                .setType(EmbeddedDatabaseType.H2)// Используем базу H2
//                .build();

        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:jdbc/schema.sql")
                .addScript("classpath:jdbc/test-data.sql")
                .build();

//        // Создадим JdbcTemplate
//        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
//
//        // Создадим BookRepository
//        bookRepository = new BookRepositoryImpl(jdbcTemplate);
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

//    @Test
//    public void testFindAll() {
//        List<Book> bookList = new ArrayList<>();
//        bookList.add(new Book (1L, "43-43", "44", "44", 45, 54, new BigDecimal(4)));
//        //when(bookRepository.findAll()).thenReturn(bookList);
//        bookRepository.create(new Book ( "43-43", "44", "44", 45, 54, new BigDecimal(4)));
//        Assert.assertNotNull(bookRepository.findAll());
//        Assert.assertEquals(1, bookRepository.findAll().size());
//    }

    @Test
    public void whenMockJdbcTemplate_thenReturnCorrectEmployeeCount() {
        BookRepository bookRepository = new BookRepositoryImpl();
        ReflectionTestUtils.setField(bookRepository, "jdbcTemplate", jdbcTemplate);
        Mockito.when(jdbcTemplate.queryForObject("SELECT * FROM BOOK where id=1", Book.class))
                .thenReturn(new Book(1L, "34-45-56", "Rest", "Tom", 456, 565,new BigDecimal(45.6)));

        assertEquals(new Book(1L, "34-45-56", "Rest", "Tom", 456, 565,new BigDecimal(45.6)), bookRepository.findById(1l));
    }
    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}