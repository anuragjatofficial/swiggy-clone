package com.swiggy.services;

import java.util.List;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Customer;

public interface ICustomerServices {
	Customer addCustomer(Customer customer) throws SwiggyException; 
	List<Customer> getAllCustomers();
	Customer getCustomerById(int customerId);
	List<Customer> getCustomerByPages(Integer page, Integer page_count, String sortBy);
}
