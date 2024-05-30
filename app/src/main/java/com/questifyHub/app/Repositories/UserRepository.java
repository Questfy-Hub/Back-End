package com.questifyHub.app.Repositories;

import com.questifyHub.app.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}

