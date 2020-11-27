package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String CREATE_USER = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    final
    JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID create(String name, String email, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);

            return ps;
        }, keyHolder);

        return (UUID) keyHolder.getKeys().get("id");
    }

    @Override
    public User findById(UUID id)  {
        return jdbcTemplate.queryForObject(FIND_USER_BY_ID, new Object[]{ id }, userRowMapper);
    }

    @Override
    public User findByEmail(String email){
        return jdbcTemplate.queryForObject(FIND_USER_BY_EMAIL, new Object[] { email }, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USERS, userRowMapper);
    }


    private final RowMapper<User> userRowMapper = ((rs, rowNum) -> new User(rs.getString("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("created_at"),
            rs.getString("updated_at")));
}
