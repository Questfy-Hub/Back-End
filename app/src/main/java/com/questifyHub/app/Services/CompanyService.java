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

/**
 * Classe CompanyService que possui a logica das funcionalidades do objeto company (empresa)
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @PersistenceContext
    private EntityManager entityManager;

    /** Método para requisitar as informações do objeto Company pelo Id
     *  
     * @param id
     * @return Objeto da classe Company
     */
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o id" + id));

    }

    /**
     * Método para requisitar uma lista de todas as empresas (Company)
     * @return Lista de todas as empresas
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    /**
     * Método para criar a empresa (company)
     * @param company
     * @return Objeto company
     */
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    /**
     * Método para alterar as informações de uma empresa (company)
     * @param id
     * @param companyDetails
     * @return Objeto company com as modificações inseridas
     */
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o id" + id));

        company.setCompanyName(companyDetails.getCompanyName());
        company.setCnpj(companyDetails.getCnpj());

        return companyRepository.save(company);

    }

    /**
     * Método para deletar o cadastro de uma empresa (company)
     * @param id
     */
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    /**
     * Método para requisitar todas as informações da classe CompanyDTO
     * @return Lista de objetos do DTO
     */
    public List<CompanyDTO> getOnlyCompany() {
        Query query = entityManager.createQuery(
                "select new com.questifyHub.app.DTOs.CompanyDTO(c.companyCode, c.companyName, c.cnpj) from Company c");
        return query.getResultList();
    }
}
