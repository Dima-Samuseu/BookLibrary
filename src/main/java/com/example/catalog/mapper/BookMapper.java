package com.example.catalog.mapper;

import com.example.catalog.model.Book;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setNumberOfPages(rs.getInt("numberOfPage"));
        book.setWeight(rs.getInt("weight"));
        book.setPrice(rs.getBigDecimal("weight"));
        return book;
    }
}
