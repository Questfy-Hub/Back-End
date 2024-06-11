package com.questifyHub.app.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.questifyHub.app.DTOs.CompanyDTO;
import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.Services.CompanyService;
@RestController
@RequestMapping("/company")
@CrossOrigin({"http://localhost:4200", "https://questfyhub.netlify.app/"})

/** Classe que faz o direcionamento das funções da entidade company (empresa)
 * 
 * @author João Paulo Rezende de Oliveira
 */
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /** Método para fazer a requisição da função getCompanyById
    * 
    * @param id
    * @return  Objeto da classe Company que recebe ID como parâmetro
    */
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    /** Método para fazer a requisição da função getAllCompanies
     * 
     * @return Lista de objetos da classe Company
     */
    @GetMapping
    public List <Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    /** Método para fazer a requisição da função createCompany
     * 
     * @param company
     * @return Objeto da classe Company que recebe company como parâmetro
     */
    @PostMapping
    public Company createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    /** Método para fazer a requisição da função updateCompany
     * 
     * @param id
     * @param company
     * @return Objeto da classe Company que recebe id e company como parâmetros 
     */
    @PatchMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    /** Método para fazer a requisição da função deleteCompany
     * 
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

    /** Método para fazer a requisição da função getSimpleCompany
     * 
     * @return Objeto da classe Company que recebe companies como parâmetro
     */
    @GetMapping("/teste")
    public ResponseEntity<List<CompanyDTO>> getSimpleCompany(){
        List<CompanyDTO> companies = companyService.getOnlyCompany();
        return ResponseEntity.ok(companies);
    }

}
