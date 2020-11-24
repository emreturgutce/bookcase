package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;

public interface UserRepository {
    String create(String name, String email, String password) throws Exception;

    User findById(String id) throws Exception;
}
