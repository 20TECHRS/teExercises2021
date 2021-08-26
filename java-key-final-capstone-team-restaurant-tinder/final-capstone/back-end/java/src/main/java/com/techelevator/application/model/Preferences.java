package com.techelevator.application.model;

import java.util.List;

public class Preferences {

    private Long    userId;
    private String  cuisineStyle1;
    private String  cuisineStyle2;
    private String  cuisineStyle3;
    private String  pricePoint;
//    private String vegan;
//    private String vegetarian;
//    private String glutenFree;
    private String dietaryRestrictions;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCuisineStyle1() {
        return cuisineStyle1;
    }

    public void setCuisineStyle1(String cuisineStyle1) {
        this.cuisineStyle1 = cuisineStyle1;
    }

    public String getCuisineStyle2() {
        return cuisineStyle2;
    }

    public void setCuisineStyle2(String cuisineStyle2) {
        this.cuisineStyle2 = cuisineStyle2;
    }

    public String getCuisineStyle3() {
        return cuisineStyle3;
    }

    public void setCuisineStyle3(String cuisineStyle3) {
        this.cuisineStyle3 = cuisineStyle3;
    }

    public String getPricePoint() {
        return pricePoint;
    }

    public void setPricePoint(String pricePoint) {
        this.pricePoint = pricePoint;
    }

//    public String getVegan() {
//        return vegan;
//    }
//
//    public void setVegan(String vegan) {
//        this.vegan = vegan;
//    }
//
//    public String getVegetarian() {
//        return vegetarian;
//    }
//
//    public void setVegetarian(String vegetarian) {
//        this.vegetarian = vegetarian;
//    }
//
//    public String getGlutenFree() {
//        return glutenFree;
//    }
//
//    public void setGlutenFree(String glutenFree) {
//        this.glutenFree = glutenFree;
//    }


    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "userId=" + userId +
                ", cuisineStyle1='" + cuisineStyle1 + '\'' +
                ", cuisineStyle2='" + cuisineStyle2 + '\'' +
                ", cuisineStyle3='" + cuisineStyle3 + '\'' +
                ", pricePoint='" + pricePoint + '\'' +
                ", dietaryRestrictions='" + dietaryRestrictions + '\'' +
                '}';
    }
}
