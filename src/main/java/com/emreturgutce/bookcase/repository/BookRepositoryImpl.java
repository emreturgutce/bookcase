package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.UUID;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private static final String CREATE_BOOK = "INSERT INTO books (name, author_id) VALUES (?, ?)";
    private static final String FIND_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";

    final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public UUID create(String name, String author_id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setObject(2, author_id, Types.OTHER);

            return ps;
        }, keyHolder);

        return (UUID) keyHolder.getKeys().get("id");
    }

    @Override
    public Book findById(UUID id) {
        return jdbcTemplate.queryForObject(FIND_BOOK_BY_ID, new Object[] { id }, bookRowMapper);
    }

    private final RowMapper<Book> bookRowMapper = ((rs, rowNum) -> new Book(rs.getString("id"),
            rs.getString("name"),
            rs.getString("author_id"),
            rs.getString("created_at"),
            rs.getString("updated_at")));
}
