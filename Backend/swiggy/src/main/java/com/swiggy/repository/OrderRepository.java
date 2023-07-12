package com.swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.swiggy.model.Orders;

public interface OrderRepository extends JpaRepository<Orders,Integer> , PagingAndSortingRepository<Orders,Integer>{

}

