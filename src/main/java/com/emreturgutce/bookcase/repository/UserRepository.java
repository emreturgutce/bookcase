package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;

import java.util.UUID;

public interface UserRepository {
    UUID create(String name, String email, String password) throws Exception;

    User findById(UUID id) throws Exception;
}
