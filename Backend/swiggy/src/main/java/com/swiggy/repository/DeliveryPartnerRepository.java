package com.swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.swiggy.model.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> ,PagingAndSortingRepository<DeliveryPartner,Integer>{

}

