package com.swiggy.services;

import java.util.List;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Restaurant;

public interface IRestaurantServices {
	Restaurant addRestaurant(Restaurant restaurant) throws SwiggyException;
	List<Restaurant> getAllRestaurants(Integer page, Integer limit, String sortBy);
	Restaurant getRestaurantById(int restaurantId);
}
