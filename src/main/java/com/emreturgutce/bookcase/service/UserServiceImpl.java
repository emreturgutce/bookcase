package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.exception.BadRequestException;
import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User signup(String name, String email, String password) throws BadRequestException {
        try {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(hashedPassword);

            userRepository.save(user);

            return user;
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
}
