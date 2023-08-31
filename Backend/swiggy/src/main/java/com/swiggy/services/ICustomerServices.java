package com.swiggy.services;

import java.util.List;

import com.swiggy.controller.AuthenticationRequest;
import com.swiggy.controller.AuthenticationResponse;
import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Customer;

public interface ICustomerServices {
	AuthenticationResponse addCustomer(Customer customer) throws SwiggyException; 
	List<Customer> getAllCustomers();
	Customer getCustomerById(int customerId);
	List<Customer> getCustomerByPages(Integer page, Integer page_count, String sortBy);
	AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
