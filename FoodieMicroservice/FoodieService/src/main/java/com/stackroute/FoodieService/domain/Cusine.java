package com.stackroute.FoodieService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cusine {
    @Id
    @GeneratedValue
    private int cusineId;
    private String cusineName;
    private String category1;
    private String category2;
    private String cusineImage;
    private double cusineCost;
    private String cusineSize;
    private String city;
    private String resturantName;
    private String email;

    public Cusine(String cusineName, String category1, String category2, String cusineImage, double cusineCost, String cusineSize, String city, String resturantName, String email) {
        this.cusineName = cusineName;
        this.category1 = category1;
        this.category2 = category2;
        this.cusineImage = cusineImage;
        this.cusineCost = cusineCost;
        this.cusineSize = cusineSize;
        this.city = city;
        this.resturantName = resturantName;
        this.email = email;
    }

    public Cusine() {
    }

    public int getCusineId() {
        return cusineId;
    }

    public void setCusineId(int cusineId) {
        this.cusineId = cusineId;
    }

    public String getCusineName() {
        return cusineName;
    }

    public void setCusineName(String cusineName) {
        this.cusineName = cusineName;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCusineImage() {
        return cusineImage;
    }

    public void setCusineImage(String cusineImage) {
        this.cusineImage = cusineImage;
    }

    public double getCusineCost() {
        return cusineCost;
    }

    public void setCusineCost(double cusineCost) {
        this.cusineCost = cusineCost;
    }

    public String getCusineSize() {
        return cusineSize;
    }

    public void setCusineSize(String cusineSize) {
        this.cusineSize = cusineSize;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
