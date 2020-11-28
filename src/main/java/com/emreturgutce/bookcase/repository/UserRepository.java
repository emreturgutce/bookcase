package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    UUID create(String name, String email, String password);

    User findById(UUID id);

    User findByEmail(String email);

    List<User> findAll();
}
