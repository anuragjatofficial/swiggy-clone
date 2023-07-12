package com.swiggy.services;

import java.util.List;

import com.swiggy.model.DeliveryPartner;

public interface IDeliveryPartnerServices {
	List<DeliveryPartner> getAllDeliveryPartners(Integer page, Integer limit, String sortBy);

	DeliveryPartner getDeliveryPartnerById(int deliveryPartnerId);

	DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner);
}
