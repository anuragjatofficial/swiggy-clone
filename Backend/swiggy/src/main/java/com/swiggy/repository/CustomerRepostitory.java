package com.swiggy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.swiggy.model.Customer;

public interface CustomerRepostitory extends JpaRepository<Customer,Integer> ,PagingAndSortingRepository<Customer,Integer>{

}

