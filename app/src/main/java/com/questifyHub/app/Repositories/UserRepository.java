package com.questifyHub.app.Repositories;

import com.questifyHub.app.Entities.User;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
//    @Query("SELECT u FROM User u JOIN u.taskUser t WHERE t.completionDate BETWEEN :startDate AND :endDate")
//    List<User> findUsersWithTasksCompletedBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}


