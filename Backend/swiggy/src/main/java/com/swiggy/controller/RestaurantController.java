package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.Restaurant;
import com.swiggy.services.IRestaurantServices;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class RestaurantController {
	@Autowired
	private IRestaurantServices iRestaurantServices;

	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer limit,@RequestParam(required = false) String sortBy) {
		return new ResponseEntity<List<Restaurant>>(iRestaurantServices.getAllRestaurants(page,limit,sortBy), HttpStatus.OK);
	}

	@GetMapping("/restaurants/{restaurantId}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int restaurantId) {
		return new ResponseEntity<Restaurant>(iRestaurantServices.getRestaurantById(restaurantId), HttpStatus.OK);
	}

	@PostMapping(value = "/restaurants")
	public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
		return new ResponseEntity<Restaurant>(iRestaurantServices.addRestaurant(restaurant), HttpStatus.CREATED);
	}
}
