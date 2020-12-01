package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.exception.NotFoundException;
import com.emreturgutce.bookcase.model.Book;
import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.repository.BookRepository;
import com.emreturgutce.bookcase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public Book create(String name, UUID author_id) throws BadRequestException{
        try {
            User author = userRepository.getOne(author_id);

            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);

            bookRepository.save(book);

            return book;
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public Book findById(UUID id) throws NotFoundException {
        try {
            return bookRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException("Book not found with the given id");
        }

    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(UUID id, Book bookParam) throws BadRequestException {
        try {
            Book book = bookRepository.findById(id).get();

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
}
