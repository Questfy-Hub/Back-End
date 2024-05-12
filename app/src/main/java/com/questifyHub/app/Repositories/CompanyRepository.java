package com.questifyHub.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.questifyHub.app.Entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
