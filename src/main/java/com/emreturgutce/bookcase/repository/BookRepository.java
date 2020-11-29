package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository {
    UUID create(String name, String author_id);

    Book findById(UUID id);

    List<Book> findAll();
}
