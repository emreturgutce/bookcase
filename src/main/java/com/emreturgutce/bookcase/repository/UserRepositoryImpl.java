package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        String FIND_BY_EMAIL = "SELECT u from User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(FIND_BY_EMAIL, User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
