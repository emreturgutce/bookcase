package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.repository.UserRepository;
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
    public User create(String name, String email, String password) throws Exception {
        UUID userId = userRepository.create(name, email, password);

        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }
}
