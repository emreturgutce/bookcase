package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {}
