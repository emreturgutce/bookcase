package com.emreturgutce.bookcase.repository;

import com.emreturgutce.bookcase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}