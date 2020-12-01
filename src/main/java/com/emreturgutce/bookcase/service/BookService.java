package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.exception.NotFoundException;
import com.emreturgutce.bookcase.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book create(String name, UUID author_id) throws BadRequestException;

    Book findById(UUID id) throws NotFoundException;

    List<Book> findAll();
//
//    void update(UUID id, Book book) throws BadRequestException;
//
//    void delete(UUID id) throws NotFoundException;
}
