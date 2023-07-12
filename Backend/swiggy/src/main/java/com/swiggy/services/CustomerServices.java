package com.swiggy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swiggy.exceptions.SwiggyException;
import com.swiggy.model.Customer;
import com.swiggy.repository.CustomerRepostitory;

@Service
public class CustomerServices implements ICustomerServices {
	@Autowired
	private CustomerRepostitory customerRepostitory;

	@Override
	public Customer addCustomer(Customer customer) throws SwiggyException {
		if (customer == null)
			throw new SwiggyException("customer value can't be null");
		if (customer.getCustomerId() != null) {
			if (customerRepostitory.existsById(customer.getCustomerId()))
				throw new SwiggyException("Customer already present in database ");
		}
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

	@Override
	public List<Customer> getCustomerByPages(Integer page, Integer limit, String sortBy) {
		if (page != null && page == 0)
			throw new SwiggyException("Page index must not be zero");
		if (sortBy != null && !sortBy.equals("customerId") && !sortBy.equals("name"))
			throw new SwiggyException("please pass correct sorting criteria");
		if (page != null && limit != null && sortBy != null) {
			Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(sortBy));
			return customerRepostitory.findAll(pageable).getContent();
		} else if(page != null && limit != null){
			Pageable pageable = PageRequest.of(page - 1, limit);
			return customerRepostitory.findAll(pageable).getContent();
		}else {
			return customerRepostitory.findAll();
		}
	}
}
