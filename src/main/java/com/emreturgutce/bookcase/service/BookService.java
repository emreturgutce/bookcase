package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.Book;

public interface BookService {
    Book create(String name, String author_id);
}
