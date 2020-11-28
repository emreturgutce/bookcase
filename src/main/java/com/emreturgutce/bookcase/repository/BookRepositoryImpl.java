package com.emreturgutce.bookcase.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class BookRepositoryImpl implements BookRepository {
    private static final String CREATE_BOOK = "INSERT INTO books (name, author_id) VALUES (?, ?)";

    final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public UUID create(String name, UUID author_id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, String.valueOf(author_id));

            return ps;
        }, keyHolder);

        return (UUID) keyHolder.getKeys().get("id");
    }
}
