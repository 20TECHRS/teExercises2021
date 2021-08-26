package com.techelevator.application.dao;

import com.techelevator.application.model.Restaurant;
import com.techelevator.application.model.RestaurantDTO;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface RestaurantDAO {
    Restaurant addRestaurant(Restaurant restaurantToAdd);

    RestaurantDTO addRestaurantToUserList(RestaurantDTO restaurantToAddToViewedList);

    Restaurant viewRestaurant(Long id);

    Restaurant updateRestaurant(Restaurant restaurantToUpdate);

    void deleteRestaurant(Long id);

    List<Restaurant> viewFavoritedRestaurants(Long id);

//    RestaurantDTO updateIfRestaurantIsLiked(RestaurantDTO restaurantToUpdate);

    void deleteRestaurantFromUsersList(RestaurantDTO restaurantToDelete);
}
