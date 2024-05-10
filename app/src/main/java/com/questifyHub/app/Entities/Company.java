package com.questifyHub.app.Entities;

import java.util.List;

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

    @OneToMany(mappedBy = "companyUser")
    private List<User> userList;


    @OneToMany(mappedBy = "companyGifts")
    private List<Gifts> giftsList;


    public Company(){}



    public Company(int companyCode, String companyName, String cnpj, List<User> userList, List<Gifts> giftsList) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.userList = userList;
        this.giftsList = giftsList;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Gifts> getGiftsList() {
        return giftsList;
    }

    public void setGiftsList(List<Gifts> giftsList) {
        this.giftsList = giftsList;
    }

    
    
}