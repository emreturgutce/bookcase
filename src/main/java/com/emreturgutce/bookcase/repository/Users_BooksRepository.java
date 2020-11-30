package com.emreturgutce.bookcase.repository;

import java.util.UUID;

public interface Users_BooksRepository {
    UUID create(UUID user_id, UUID book_id);
}
