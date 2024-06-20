package com.questifyHub.app.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

/** Classe Company onde é gerado a entidade Company (empresa)
 * @author João Paulo Rezende de Oliveira
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "companyCode")
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


    /** Constructor da entidade Company
     * @param companyCode
     * @param companyName
     * @param cnpj
     * @param userList
     * @param giftsList
     */

    public Company(int companyCode, String companyName, String cnpj, List<User> userList, List<Gifts> giftsList) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.userList = userList;
        this.giftsList = giftsList;
    }


    //Getters e Setters
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
