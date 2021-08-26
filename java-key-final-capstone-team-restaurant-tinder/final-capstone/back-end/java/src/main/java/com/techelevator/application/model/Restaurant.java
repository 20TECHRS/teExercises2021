package com.techelevator.application.model;

import java.util.List;

public class Restaurant {

    private Long restaurantId;
    private Long locationId;
    private String restaurantName;
    private String description;
    private String imageLink;
    private String restaurantPhone;
    private String restaurantWebsite;
    private String priceRange;
    private List<Cuisine> cuisineTypes;
    private String address;
    private Double lat;
    private Double lon;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantWebsite() {
        return restaurantWebsite;
    }

    public void setRestaurantWebsite(String restaurantWebsite) {
        this.restaurantWebsite = restaurantWebsite;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public List<Cuisine> getCuisineTypes() {
        return cuisineTypes;
    }

    public void setCuisineTypes(List<Cuisine> cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", locationId=" + locationId +
                ", restaurantName='" + restaurantName + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", restaurantPhone='" + restaurantPhone + '\'' +
                ", restaurantWebsite='" + restaurantWebsite + '\'' +
                ", priceRange='" + priceRange + '\'' +
                ", cuisineTypes=" + cuisineTypes +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
