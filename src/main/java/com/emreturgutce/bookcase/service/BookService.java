package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.exception.NotFoundException;
import com.emreturgutce.bookcase.model.Book;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface BookService {
    CompletableFuture<Book> create(String name, UUID author_id) throws BadRequestException;

    Book findById(UUID id) throws NotFoundException;

    CompletableFuture<List<Book>> findAll();

    Book update(UUID id, Book book) throws BadRequestException;

    void delete(UUID id) throws NotFoundException;

    void addToFavorite(UUID bookId, UUID userId) throws NotFoundException;
}
