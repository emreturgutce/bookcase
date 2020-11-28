package com.emreturgutce.bookcase.repository;

import java.util.UUID;

public interface BookRepository {
    UUID create(String name, UUID author_id);
}
