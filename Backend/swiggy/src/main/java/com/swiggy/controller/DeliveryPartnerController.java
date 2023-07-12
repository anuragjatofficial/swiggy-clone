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

import com.swiggy.model.DeliveryPartner;
import com.swiggy.services.IDeliveryPartnerServices;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class DeliveryPartnerController {
	@Autowired
	private IDeliveryPartnerServices iDeliveryPartnerServices;

	@GetMapping("/deliveryPartners")
	public ResponseEntity<List<DeliveryPartner>> getAllDeliveryPartners(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer limit,@RequestParam(required = false) String sortBy) {
		return new ResponseEntity<List<DeliveryPartner>>(iDeliveryPartnerServices.getAllDeliveryPartners(page,limit,sortBy), HttpStatus.OK);
	}

	@PostMapping("/deliveryPartners")
	public ResponseEntity<DeliveryPartner> addDeliveryPartner(@Valid @RequestBody DeliveryPartner deliveryPartner) {
		return new ResponseEntity<DeliveryPartner>(iDeliveryPartnerServices.addDeliveryPartner(deliveryPartner),
				HttpStatus.CREATED);
	}

	@GetMapping("/deliveryPartners/{deliveryPartnerId}")
	public ResponseEntity<DeliveryPartner> getDeliveryPartnerById(@PathVariable int deliveryPartnerId) {
		return new ResponseEntity<DeliveryPartner>(iDeliveryPartnerServices.getDeliveryPartnerById(deliveryPartnerId),
				HttpStatus.OK);
	}
}
