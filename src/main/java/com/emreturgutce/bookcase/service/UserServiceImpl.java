package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signup(String name, String email, String password) throws BadRequestException {
        try {
            UUID userId = userRepository.create(name, email, password);

            return userRepository.findById(userId);
        } catch (Exception e) {
            throw new BadRequestException("invalid credentials");
        }
    }

    @Override
    public User login(String email, String password) throws BadRequestException {
        try {
            User user = userRepository.findByEmail(email);

            boolean result = BCrypt.checkpw(password, user.getPassword());

            if (!result) {
                throw new Exception();
            }

            return user;
        } catch (Exception e) {
            throw new BadRequestException("Invalid credentials");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
