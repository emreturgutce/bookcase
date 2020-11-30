package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.exception.NotFoundException;
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
    public Book findById(UUID id) throws NotFoundException {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException("Book not found with the given id");
        }

    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void update(UUID id, Book book) throws BadRequestException {
        try {
            bookRepository.update(id, book.getName(), UUID.fromString(book.getAuthor_id()));
        } catch (Exception e) {
            throw new BadRequestException("invalid request");
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            bookRepository.delete(id);
        } catch (Exception e) {
            throw new BadRequestException("invalid request");
        }
    }
}
