package com.example.catalog.repository.impl;

import com.example.catalog.mapper.BookMapper;
import com.example.catalog.model.Book;
import com.example.catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
//    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Book> findAll() {
        String sql = "select * from book";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public Book findById(Long id) {
        String sql = "select * from book where id = ?";
        try {
            return this.jdbcTemplate.queryForObject(
                    sql, new Object[]{id}, new BookMapper());
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    public List<Book> findByAuthor(String author) {
        String sql = "select * from book where author = ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + author + "%"}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void create (Book book) {
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
