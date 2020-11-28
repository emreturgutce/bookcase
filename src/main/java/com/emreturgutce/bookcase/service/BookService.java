package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.Book;

import java.util.UUID;

public interface BookService {
    Book create(String name, UUID author_id);
}
