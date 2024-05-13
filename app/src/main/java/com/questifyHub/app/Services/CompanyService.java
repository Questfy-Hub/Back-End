package com.questifyHub.app.Services;

import java.util.List;

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

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company creatCompany(Company company){
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company companyDetails){
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o id"+id));

        company.setCompanyName(companyDetails.getCompanyName());
        company.setCnpj(companyDetails.getCnpj());

        return companyRepository.save(company);

    }

    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }
}
