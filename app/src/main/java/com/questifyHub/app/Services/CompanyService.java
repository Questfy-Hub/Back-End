package com.questifyHub.app.Services;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.DTOs.CompanyDTO;
import com.questifyHub.app.Exceptions.ResourceNotFoundException;
import com.questifyHub.app.Repositories.CompanyRepository;



@Service
public class CompanyService {

    
    
    @Autowired
    private CompanyRepository companyRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Company getCompanyById(Long id){
        return companyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o id"+id));

    }
    //TODO: Tratamento de Excessão
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    //TODO: Tratamento de Excessão
    public Company createCompany(Company company){
        return companyRepository.save(company);
    }
    //TODO: Tratamento de Excessão
    public Company updateCompany(Long id, Company companyDetails){
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o id"+id));

        company.setCompanyName(companyDetails.getCompanyName());
        company.setCnpj(companyDetails.getCnpj());

        return companyRepository.save(company);

    }
    //TODO: Tratamento de Excessão
    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

    /*
    Arruma dps otario!

    Ta arrumado bbk
     */
    public List<CompanyDTO> getOnlyCompany(){
        Query query = entityManager.createQuery("select new com.questifyHub.app.DTOs.CompanyDTO(c.companyCode, c.companyName, c.cnpj) from Company c");
        return query.getResultList();
    }
}
