package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.User;

import java.util.List;

public interface UserService {
    User create(String name, String email, String password) throws Exception;

    List<User> findAll() throws Exception;
}
