package com.swiggy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.swiggy.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> ,PagingAndSortingRepository<Restaurant,Integer> {

}
