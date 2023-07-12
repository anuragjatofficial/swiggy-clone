package com.swiggy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.DeliveryPartner;
import com.swiggy.repository.DeliveryPartnerRepository;

@Service
public class DeliveryPartnerServices implements IDeliveryPartnerServices {
	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;

	@Override
	public List<DeliveryPartner> getAllDeliveryPartners(Integer page, Integer limit, String sortBy) {
		if (page != null && page == 0)
			throw new SwiggyException("Page index must not be zero");
		if (sortBy != null && !sortBy.equals("deliveryPartnerId") && !sortBy.equals("name"))
			throw new SwiggyException("please pass correct sorting criteria");
		if (page != null && limit != null && sortBy != null) {
			Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(sortBy));
			return deliveryPartnerRepository.findAll(pageable).getContent();
		} else if (page != null && limit != null) {
			Pageable pageable = PageRequest.of(page - 1, limit);
			return deliveryPartnerRepository.findAll(pageable).getContent();
		} else {
			return deliveryPartnerRepository.findAll();
		}
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
