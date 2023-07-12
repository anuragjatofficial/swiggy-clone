package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.Customer;
import com.swiggy.services.ICustomerServices;

import jakarta.validation.Valid;

@RestController
public class CustomerController {
	@Autowired
	private ICustomerServices iCustomerServices;

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(iCustomerServices.getAllCustomers(), HttpStatus.OK);
	}

	// -- add customer -- //

	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		return new ResponseEntity<Customer>(iCustomerServices.addCustomer(customer), HttpStatus.CREATED);
	}

//	@PostMapping(value = "/customer")
//	public ResponseEntity<Customer> addCustomer()

	// -- get customer by id -- //

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
		return new ResponseEntity<Customer>(iCustomerServices.getCustomerById(customerId), HttpStatus.OK);
	}
}
