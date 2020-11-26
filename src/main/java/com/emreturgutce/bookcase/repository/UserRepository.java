package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    UUID create(String name, String email, String password) throws Exception;

    User findById(UUID id) throws Exception;

    User findByEmail(String email) throws Exception;

    List<User> findAll() throws Exception;
}
