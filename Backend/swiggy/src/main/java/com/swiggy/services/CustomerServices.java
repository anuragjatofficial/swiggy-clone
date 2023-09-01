package com.swiggy.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.swiggy.controller.AuthenticationRequest;
import com.swiggy.controller.AuthenticationResponse;
import com.swiggy.exceptions.SwiggyException;
import com.swiggy.exceptions.UserNotFoundException;
import com.swiggy.model.Customer;
import com.swiggy.model.User;
import com.swiggy.repository.CustomerRepostitory;
import com.swiggy.repository.UserRepository;
import com.swiggy.utils.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServices implements ICustomerServices {
	@Autowired
	private CustomerRepostitory customerRepostitory;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse addCustomer(Customer customer) throws SwiggyException {
		// registerCustomer
		if (customer == null)
			throw new SwiggyException("customer value can't be null");
		if (customer.getUserId() != null) {
			if (customerRepostitory.existsById(customer.getUserId()))
				throw new SwiggyException("Customer already present in database ");
		}
		log.info("customer added");
		customerRepostitory.save(customer);
		var jwtToken = jwtService.generateToken(customer);
		return new AuthenticationResponse(
				jwtToken,
				new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()+1000*60*24)
		);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepostitory.findAll();
	}

	@Override
	public Customer getCustomerById(int userId) {
		return customerRepostitory.findById(userId)
				.orElseThrow(() -> new SwiggyException("can't find any customer with id " + userId));
	}

	@Override
	public List<Customer> getCustomerByPages(Integer page, Integer limit, String sortBy) {
		if (page != null && page == 0)
			throw new SwiggyException("Page index must not be zero");
		
		if (sortBy != null && !sortBy.equals("userId") && !sortBy.equals("name"))
			throw new SwiggyException("please pass correct sorting criteria");
		
		if (page != null && limit != null && sortBy != null) {
			
			Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(sortBy));
			return customerRepostitory.findAll(pageable).getContent();
			
		} else if (page != null && limit != null) {
			
			Pageable pageable = PageRequest.of(page - 1, limit);
			return customerRepostitory.findAll(pageable).getContent();
			
		} else {
			
			return customerRepostitory.findAll();
			
		}
	}

	@Override
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest
						.getUsername(),
						authenticationRequest
						.getPassword()
				)
		); 
		
		User user = userRepository.findByUsername(
						authenticationRequest.getUsername()
					)
					.orElseThrow(
							()->new UserNotFoundException(
										"can't find any user with username "+authenticationRequest.getUsername()
									)
					);
		
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(
					jwtToken,
					new Date(
							System.currentTimeMillis()
					),
					new Date(
							System.currentTimeMillis()+1000*60*24
					)
		);
	}
}
