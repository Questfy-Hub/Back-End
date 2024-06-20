package com.questifyHub.app.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/** Classe Company onde é gerado a entidade Gifts (recompensas)
 * @author João Paulo Rezende de Oliveira
 */
@Entity
@Table(name = "gifts")
public class Gifts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int giftCode;

    @Column(name = "giftName")
    private String giftName;

    @Column(name = "price")
    private Double price;

    @Column(name = "category")
    private String category;

    private byte[] image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_companyCode")
    private Company companyGifts;


    /** Constructor Vazio
     * 
     */
    public Gifts(){}


    /** Constructor da entidade Gifts
     * 
     * @param giftCode
     * @param giftName
     * @param price
     * @param category
     * @param companyGifts
     */
    public Gifts(int giftCode, String giftName, Double price, String category, byte[] image, Company companyGifts) {
        this.giftCode = giftCode;
        this.giftName = giftName;
        this.price = price;
        this.category = category;
        this.image = image;
        this.companyGifts = companyGifts;
    }

    //Getters e Setters
    public int getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(int giftCode) {
        this.giftCode = giftCode;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    

    public Company getCompanyGifts() {
        return companyGifts;
    }

    public void setCompanyGifts(Company companyGifts) {
        this.companyGifts = companyGifts;
    }


    public byte[] getImage() {
        return image;
    }


    public void setImage(byte[] image) {
        this.image = image;
    }
    
    
    
}
