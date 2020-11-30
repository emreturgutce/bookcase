package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class Users_BooksServiceImpl implements Users_BooksService {
    final Users_BooksRepository users_booksRepository;

    public Users_BooksServiceImpl(Users_BooksRepository users_booksRepository) {
        this.users_booksRepository = users_booksRepository;
    }

    @Override
    public void create(UUID user_id, UUID book_id) throws BadRequestException {
        try {
            users_booksRepository.create(user_id, book_id);
        } catch (Exception e) {
            throw new BadRequestException("Relation already exists");
        }
    }
}
