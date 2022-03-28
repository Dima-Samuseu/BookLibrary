package com.example.catalog.repository.impl;

import com.example.catalog.mapper.BookMapper;
import com.example.catalog.model.Book;
import com.example.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getBook() {
        String sql = "select * from book";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public Book getOneBook(Long id) throws DataAccessException {
        String sql = "select * from book where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
    }

    public List<Book> findByAuthor(String author) {
        String sql = "select * from book where author = ?";
        return jdbcTemplate.query(sql, new Object[]{author}, new BookMapper());
    }

    public List<Book> findByName(String name) {
        String sql = "select * from book where name = ?";
        return jdbcTemplate.query(sql, new Object[]{name}, new BookMapper());
    }

    public void create(Book book) {
        String sql = "insert into book (isbn, name, author, number_of_pages, weight, price) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, book.getIsbn(), book.getName(), book.getAuthor(), book.getNumberOfPages(),
                book.getWeight(), book.getPrice());
    }

    public void update(Book book) {
        String sql = "update book set isbn=?, name=?, author=?, number_of_pages=?, weight=?, price=? WHERE id=?";
        jdbcTemplate.update(sql, book.getIsbn(), book.getName(), book.getAuthor(), book.getNumberOfPages(),
                book.getWeight(), book.getPrice(), book.getId());
    }

    public void remove(Long id) {
        String sql = "delete from book where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
