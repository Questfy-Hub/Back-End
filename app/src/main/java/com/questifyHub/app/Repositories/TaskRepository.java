package com.questifyHub.app.Repositories;

import com.questifyHub.app.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}