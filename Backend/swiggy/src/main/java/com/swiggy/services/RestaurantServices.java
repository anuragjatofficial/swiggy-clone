package com.swiggy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Restaurant;
import com.swiggy.repository.RestaurantRepository;

@Service
public class RestaurantServices implements IRestaurantServices {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new SwiggyException("can't find any restaurant with id " + restaurantId));
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws SwiggyException {
		if (restaurant == null)
			throw new SwiggyException("restaurant  value can't be null");
		if (restaurant.getRestaurantId() != null) {

			Restaurant restaurant2 = restaurantRepository.findById(restaurant.getRestaurantId()).orElse(null);
			if (restaurant2 != null)
				throw new SwiggyException("Restaurant already present in database ");
		}
		return restaurantRepository.save(restaurant);
	}

}
