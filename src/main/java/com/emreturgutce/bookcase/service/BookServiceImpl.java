package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.exception.NotFoundException;
import com.emreturgutce.bookcase.model.Book;
import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.repository.BookRepository;
import com.emreturgutce.bookcase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    @Async
    public CompletableFuture<Book> create(String name, UUID author_id) throws BadRequestException{
        try {
            User author = userRepository.getOne(author_id);

            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);

            bookRepository.save(book);

            return CompletableFuture.completedFuture(book);
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    @Async
    public CompletableFuture<Book> findById(UUID id) throws NotFoundException {
        try {
            return CompletableFuture.completedFuture(bookRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new NotFoundException("Book not found with the given id");
        }
    }

    @Override
    @Async
    public CompletableFuture<List<Book>> findAll() {
        return CompletableFuture.completedFuture(bookRepository.findAll());
    }

    @Override
    public Book update(UUID id, Book bookParam) throws BadRequestException {
        try {
            Book book = bookRepository.getOne(id);

            book.setName(bookParam.getName());

            bookRepository.save(book);

            return book;
        } catch (Exception e) {
            throw new BadRequestException("invalid request");
        }
    }

    @Override
    public void delete(UUID id) throws NotFoundException {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Book not found");
        }
    }

    @Override
    public void addToFavorite(UUID bookId, UUID userId) throws NotFoundException {
        try {
            User user = userRepository.getOne(userId);
            Book book = bookRepository.getOne(bookId);

            List<Book> books = user.getBooks();

            books.add(book);

            user.setBooks(books);

            userRepository.save(user);
        } catch (Exception e) {
            throw new NotFoundException("Not found");
        }
    }
}
