package com.questifyHub.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questifyHub.app.Entities.Company;
import com.questifyHub.app.Services.CompanyService;
@RestController
@RequestMapping("/Company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @GetMapping("/Company/{id}")
    public Company getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }

    @GetMapping
    public List <Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public Company creatCompany(@RequestBody Company company){
        return companyService.creatCompany(company);
    }

    @PutMapping("/Company/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company){
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/Company/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

}