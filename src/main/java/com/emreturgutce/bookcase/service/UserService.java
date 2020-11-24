package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.User;

public interface UserService {
    User create(String name, String email, String password) throws Exception;
}
