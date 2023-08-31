package com.swiggy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.model.Customer;
import com.swiggy.model.User;
import com.swiggy.services.ICustomerServices;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@CrossOrigin
@Slf4j
public class CustomerController {
	
	@Autowired
	private ICustomerServices iCustomerServices;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * [GET] URL -> http://localhost:8080/customers
	 */
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomerByPage(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer limit, @RequestParam(required = false) String sortBy) {
		return new ResponseEntity<List<Customer>>(iCustomerServices.getCustomerByPages(page, limit, sortBy),
				HttpStatus.OK);
	}

	/**
	 * [POST] URL -> http://localhost:8080/customers
	 * 
	 * Request body 
	 * { 
	 * 	"username":"johndoe_12",
	 *  "password":"jane_1233",
	 *  "role":"USER",
	 *  "name":"john doe", 
	 *  "email":"johndoe123@gmail.com"
	 * }
	 * 
	 */

	@PostMapping("/customers")
	public ResponseEntity<AuthenticationResponse> addCustomer(@Valid @RequestBody Customer customer) {
		log.info("customer api got called to \"/customers\" endpoint ");
		if (customer.getUserId() != null)
			log.warn("customer should not have id ");
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return new ResponseEntity<AuthenticationResponse>(iCustomerServices.addCustomer(customer), HttpStatus.CREATED);
	}

	/**
	 * [GET] URL -> http://localhost:8080/customers
	 */

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
		return new ResponseEntity<Customer>(iCustomerServices.getCustomerById(customerId), HttpStatus.OK);
	}
	
	// -- login customer with credentials -- //

	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) {
		log.info("customer api got called to \"/signin\" endpoint ");
		return new ResponseEntity<AuthenticationResponse>(iCustomerServices.login(authRequest),HttpStatus.ACCEPTED);
	}
	
}
