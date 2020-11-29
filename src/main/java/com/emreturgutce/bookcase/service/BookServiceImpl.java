package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.Book;
import com.emreturgutce.bookcase.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) { this.bookRepository = bookRepository; }

    @Override
    public Book create(String name, String author_id) {
        UUID bookId = bookRepository.create(name, author_id);

        return bookRepository.findById(bookId);
    }

    @Override
    public Book findById(UUID id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
