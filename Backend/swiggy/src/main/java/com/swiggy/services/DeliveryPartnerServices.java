package com.swiggy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.DeliveryPartner;
import com.swiggy.repository.DeliveryPartnerRepository;
@Service
public class DeliveryPartnerServices implements IDeliveryPartnerServices{
	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;
	
	@Override
	public List<DeliveryPartner> getAllDeliveryPartners() {
		return deliveryPartnerRepository.findAll();
	}

	@Override
	public DeliveryPartner getDeliveryPartnerById(int deliveryPartnerId) {
		return deliveryPartnerRepository.findById(deliveryPartnerId)
				.orElseThrow(() -> new SwiggyException("can't find any delivery partner with id " + deliveryPartnerId));
	}
	
	@Override
	public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) {
//		if(deliveryPartnerRepository.findById(deliveryPartner.getDeliveryPartnerId()).orElse(null) != null) {
//			throw new SwiggyException("delivery person already available in database ");
//		}
		return deliveryPartnerRepository.save(deliveryPartner);
	}
}
