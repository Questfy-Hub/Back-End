package com.questifyHub.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.questifyHub.app.Entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
    
}
