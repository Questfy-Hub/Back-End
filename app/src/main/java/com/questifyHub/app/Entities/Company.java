package com.questifyHub.app.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyCode;
    @Column(name="companyName", unique = true)
    private String companyName;
    @Column(name="cnpj")
    private String cnpj;

    public Company(){}

    public Company(int companyCode, String companyName, String cnpj) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.cnpj = cnpj;
    }

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
