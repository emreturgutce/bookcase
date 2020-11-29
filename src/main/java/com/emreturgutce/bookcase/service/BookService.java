package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book create(String name, String author_id);

    Book findById(UUID id);

    List<Book> findAll();

    void update(UUID id, Book book) throws BadRequestException;
}
