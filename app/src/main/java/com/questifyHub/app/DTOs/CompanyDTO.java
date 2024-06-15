package com.questifyHub.app.DTOs;

/**
 * Classe CompanyDTO para transporte dos dados do objeto company (empresa) para
 * melhor visualização
 * 
 */
public class CompanyDTO {
    private int companyCode;
    private String companyName;
    private String cnpj;

    /**
     * Cronstructor vazio
     * 
     */
    public CompanyDTO() {
    }

    /**
     * Constructor do DTO de empresa
     * 
     * @param companyCode
     * @param companyName
     * @param cnpj
     */
    public CompanyDTO(int companyCode, String companyName, String cnpj) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.cnpj = cnpj;
    }

    // Getters e Setters
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