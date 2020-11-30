package com.emreturgutce.bookcase.service;

import java.util.UUID;

public interface Users_BooksService {
    void create(UUID user_id, UUID book_id);
}
