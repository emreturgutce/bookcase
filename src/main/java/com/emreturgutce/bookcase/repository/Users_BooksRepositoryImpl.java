package com.emreturgutce.bookcase.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.UUID;

@Repository
public class Users_BooksRepositoryImpl implements Users_BooksRepository {
    private static final String CREATE_USERS_BOOKS = "INSERT INTO users_books (user_id, book_id) VALUES (?, ?)";

    final JdbcTemplate jdbcTemplate;

    public Users_BooksRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID create(UUID user_id, UUID book_id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_USERS_BOOKS, Statement.RETURN_GENERATED_KEYS);

            ps.setObject(1, user_id, Types.OTHER);
            ps.setObject(2, book_id, Types.OTHER);

            return ps;
        }, keyHolder);

        return (UUID) keyHolder.getKeys().get("id");
    }
}
