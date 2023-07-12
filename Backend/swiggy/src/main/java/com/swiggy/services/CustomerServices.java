package com.swiggy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Customer;
import com.swiggy.repository.CustomerRepostitory;
import com.swiggy.repository.OrderRepository;
@Service
public class CustomerServices implements ICustomerServices {
	@Autowired
	private CustomerRepostitory customerRepostitory;
	
	@Override
	public Customer addCustomer(Customer customer) throws SwiggyException {
		if (customer == null)
			throw new SwiggyException("customer value can't be null");
//		if(customer.getCustomerId()!=null) {
//		 if (customerRepostitory.existsById(customer.getCustomerId()))
//			throw new SwiggyException("Customer already present in database ");
//		}
		return customerRepostitory.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepostitory.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return customerRepostitory.findById(customerId)
				.orElseThrow(() -> new SwiggyException("can't find any customer with id " + customerId));
	}
}
