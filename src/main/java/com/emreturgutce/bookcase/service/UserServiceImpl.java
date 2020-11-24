package com.emreturgutce.bookcase.service;

import com.emreturgutce.bookcase.model.User;
import com.emreturgutce.bookcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(String name, String email, String password) throws Exception {
        String userId = userRepository.create(name, email, password);

        return userRepository.findById(userId);
    }
}
