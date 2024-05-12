package com.questifyHub.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyById(Long id){
        return companyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o id"+id));

    }
}
