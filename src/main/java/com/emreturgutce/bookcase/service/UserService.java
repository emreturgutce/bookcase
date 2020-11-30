package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.exception.UnauthorizedException;
import com.emreturgutce.bookcase.model.User;

public interface UserService {
    User signup(String name, String email, String password) throws BadRequestException;

    User login(String email, String password) throws UnauthorizedException;
}
