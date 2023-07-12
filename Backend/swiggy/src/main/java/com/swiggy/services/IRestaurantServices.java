package com.swiggy.services;

import java.util.List;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Restaurant;

public interface IRestaurantServices {
	Restaurant addRestaurant(Restaurant restaurant) throws SwiggyException;
	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurantById(int restaurantId);
}
