package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.Book;

import java.util.List;

public interface BookService {
    Book create(String name, String author_id);

    List<Book> findAll();
}
