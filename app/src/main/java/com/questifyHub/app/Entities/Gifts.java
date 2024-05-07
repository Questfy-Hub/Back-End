package com.questifyHub.app.Entities;

import jakarta.persistence.*;

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

    public Gifts(){}

    public Gifts(int giftCode, String giftName, Double price, String category) {
        this.giftCode = giftCode;
        this.giftName = giftName;
        this.price = price;
        this.category = category;
    }

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
    
    
}
