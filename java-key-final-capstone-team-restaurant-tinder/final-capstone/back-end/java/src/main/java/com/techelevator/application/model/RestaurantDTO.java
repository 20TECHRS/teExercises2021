package com.techelevator.application.model;

import org.springframework.security.core.userdetails.User;

public class RestaurantDTO {
    private Restaurant restaurantToAdd;
    private Profile currentProfile;

    public Restaurant getRestaurantToAdd() {
        return restaurantToAdd;
    }

    public void setRestaurantToAdd(Restaurant restaurantToAdd) {
        this.restaurantToAdd = restaurantToAdd;
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }


    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "restaurantToAdd=" + restaurantToAdd +
                ", currentProfile=" + currentProfile +
                "}";
    }
}